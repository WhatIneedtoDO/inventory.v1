<template>
    <div class="popup-user popup-active">
        <div class="popup-new-user-container">
            <button class="popup_close" @click="closePopup">X</button>
            <form @submit.prevent="handleSubmit" class="popup-new-user_form">
                <h2 style="text-align: center;">Добавление нового компьютера</h2>
                <input v-model="InvCard" type="number" placeholder="Инвентарная карта" class="popup-new-user_form-input"/>
                <input v-model="SerialNum" type="text" placeholder="Серийный номер" class="popup-new-user_form-input"/>
                <input v-model="InvNum" type="text" placeholder="Инвентарный номер" class="popup-new-user_form-input"/>
                <div class="main-page_form-sort" style="gap: 0;">
                    <p class="main-page_form-input-text" style="max-width: 30%;">Производитель</p>
                    <select v-model="manufacturer" class="main-page_form-input" style="max-width: 70%;">
                        <option class="main-page_form-option" selected value="0">-</option>
                        <option v-for="item in manufacturerOptions" :key="item.id" :value="item.id">{{
                            item.name
                            }}
                        </option>
                    </select>
                </div>
                <div class="main-page_form-sort" style="gap: 0;">
                    <p class="main-page_form-input-text" style="max-width: 30%;">Модель</p>
                    <select v-model="model" class="main-page_form-input" style="max-width: 70%;">
                        <option class="main-page_form-option" selected value="0">-</option>
                        <option v-for="item in modelOptions" :key="item.id" :value="item.id">{{ item.name }}</option>
                    </select>
                </div>
                <input v-model="ROM" type="text" placeholder="Объем физической памяти"
                       class="popup-new-user_form-input"/>
                <input v-model="RAM" type="text" placeholder="Объем ОЗУ" class="popup-new-user_form-input"/>
                <input v-model="bpPower" type="text" placeholder="Мощность БП" class="popup-new-user_form-input"/>
                <input v-model="year" type="text" placeholder="Год" class="popup-new-user_form-input"/>
                <div class="main-page_form-sort" style="gap: 0;">
                    <p class="main-page_form-input-text" style="max-width: 30%;">Состояние</p>
                    <select v-model="service" class="main-page_form-input" style="max-width: 70%;">
                        <option class="main-page_form-option" selected value="0">-</option>
                        <option class="main-page_form-option" value="HAVEPROBLEM">Имеются проблемы</option>
                        <option class="main-page_form-option" value="NOPROBLEM">Нет проблем</option>
                    </select>
                </div>
                <div class="main-page_form-sort" style="gap: 0;">
                    <p class="main-page_form-input-text" style="max-width: 30%;">Тип оборудования</p>
                    <select v-model="itemType" class="main-page_form-input" style="max-width: 70%;">
                        <option class="main-page_form-option" selected value="0">-</option>
                        <option v-for="item in itemTypeOptions" :key="item.id" :value="item.id">{{ item.name }}</option>
                    </select>
                </div>
                <div class="main-page_form-sort" style="gap: 0;">
                    <p class="main-page_form-input-text" style="max-width: 30%;">Город</p>
                    <select v-model="City" class="main-page_form-input" style="max-width: 70%;">
                        <option class="main-page_form-option" selected value="0">-</option>
                        <option v-for="item in CityOptions" :key="item.id" :value="item.id">{{ item.name }}</option>
                    </select>
                </div>
                <div class="main-page_form-sort" style="gap: 0;">
                    <p class="main-page_form-input-text" style="max-width: 30%;">Местонахождение</p>
                    <select v-model="Location" class="main-page_form-input" style="max-width: 70%;">
                        <option class="main-page_form-option" selected value="0">-</option>
                        <option v-for="item in LocationOptions" :key="item.id" :value="item.id">{{ item.street +", "+ item.number +", "+ item.ekp }}</option>
                    </select>
                </div>
                <input v-model="Room" type="text" placeholder="Комната" class="popup-new-user_form-input"/>
                <div class="main-page_form-sort" style="gap: 0;">
                    <p class="main-page_form-input-text" style="max-width: 30%;">Ответственное лицо</p>
                    <select v-model="userID" class="main-page_form-input" style="max-width: 70%;">
                        <option class="main-page_form-option" selected value="0">-</option>
                        <option v-for="item in userIDOptions" :key="item.id" :value="item.id">{{ item.username }}</option>
                    </select>
                </div>
                <input v-model="staydate" type="date" placeholder="Дата постановки" class="popup-new-user_form-input"/>
                <input v-model="price" type="number" placeholder="Цена" class="popup-new-user_form-input"/>
                <input v-model="Comment" type="text" placeholder="Комментарий" class="popup-new-user_form-input"/>
                <button type="submit" class="popup-new-user_form-submit">Добавить</button>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            InvCard: '',
            manufacturer: 0,
            manufacturerOptions: [],
            SerialNum: '',
            InvNum: '',
            model: 0,
            RAM: '',
            ROM: '',
            bpPower: '',
            year: '',
            service: 0,
            modelOptions: [],
            itemType: 0,
            itemTypeOptions: [],
            City: 0,
            CityOptions: [],
            Location: 0,
            LocationOptions: [],
            Room: '',
            userID: 0,
            userIDOptions: [],
            staydate: '',
            price: '',
            Comment: '',
        };
    },
    async created() {
        this.manufacturerOptions = await this.fetchData("http://26.13.163.62:8081/api/v1/Production/all");
        this.modelOptions = await this.fetchData("http://26.13.163.62:8081/api/v1/Model/all");
        this.itemTypeOptions = await this.fetchData("http://26.13.163.62:8081/api/v1/Type/all");
        this.CityOptions = await this.fetchData("http://26.13.163.62:8081/api/v1/City/all");
        this.LocationOptions = await this.fetchData("http://26.13.163.62:8081/api/v1/Location/all");
        this.userIDOptions = await this.fetchData("http://26.13.163.62:8081/api/v1/admin/Users");
    },
    methods: {
        async handleSubmit() {
            const payload = {
                i_card: this.InvCard,
                serialnumber: this.SerialNum,
                i_number: this.InvNum,
                production: this.manufacturer,
                model: this.model,
                itemType: this.itemType,
                ssd: "VALUE" + this.ROM,
                ram: "VALUE" + this.RAM,
                bp: this.bpPower,
                year: this.year,
                serv: this.service,
                city: this.City,
                location: this.Location,
                room: this.Room,
                userId: this.userID,
                staydate: "2023-07-21",
                price: this.price,
                comment: this.Comment,
            };
            console.log(payload);
            const ApiAddComp =
                "http://26.13.163.62:8081/api/v1/Computers/add";
            const token = localStorage.getItem("jwtToken");
            const requestOptions = {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(payload),
            };
            try {
                const response = await fetch(ApiAddComp, requestOptions);
                this.closePopup();
            } catch (error) {
                console.error("Error:", error);
            }
        },
        async fetchData(EndPoint) {
            const token = localStorage.getItem("jwtToken");
            const requestOptions = {
                method: "GET",
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json", // Adjust the content type as needed
                },
            };
            try {
                const response = await fetch(EndPoint, requestOptions);
                return response.json();
            } catch (error) {
                console.error("Error fetching data:", error);
                return []; // Возвращаем пустой массив в случае ошибки
            }
        },
        closePopup() {
            this.$emit('close');
        },
    },
};
</script>

<style scoped>
/* Стили для .popup-user и других общих стилей */
</style>
