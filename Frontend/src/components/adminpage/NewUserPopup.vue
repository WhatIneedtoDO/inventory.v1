<template>
    <div class="popup-user popup-active">
        <div class="popup-new-user-container">
            <button class="popup_close" @click="closePopup">X</button>
            <form @submit.prevent="handleSubmit" class="popup-new-user_form">
                <h2 style="text-align: center;">Добавление нового пользователя</h2>
                <input v-model="userName" type="text" placeholder="User Name" class="popup-new-user_form-input" />
                <input v-model="lastName" type="text" placeholder="Фамилия" class="popup-new-user_form-input" />
                <input v-model="firstName" type="text" placeholder="Имя" class="popup-new-user_form-input" />
                <input v-model="patronymic" type="text" placeholder="Отчество" class="popup-new-user_form-input" />
                <div class="main-page_form-sort" style="gap: 0;">
                    <p class="main-page_form-input-text" style="max-width: 30%;">Отдел</p>
                    <select v-model="department" class="main-page_form-input" style="max-width: 70%;">
                        <option class="main-page_form-option" selected value="0">-</option>
                        <option class="main-page_form-option" value="1">ЭТИ</option>
                        <option class="main-page_form-option" value="2">АТИ</option>
                        <option class="main-page_form-option" value="3">БУХГАЛТЕР</option>
                    </select>
                </div>
                <div class="main-page_form-sort" style="gap: 0;">
                    <p class="main-page_form-input-text" style="max-width: 30%;">Роль</p>
                    <select v-model="role" class="main-page_form-input" style="max-width: 70%;">
                        <option class="main-page_form-option" selected value="0">-</option>
                        <option class="main-page_form-option" value="1">User</option>
                        <option class="main-page_form-option" value="2">Super User</option>
                        <option class="main-page_form-option" value="3">Admin</option>
                    </select>
                </div>
                <input v-model="password" type="text" placeholder="Пароль" class="popup-new-user_form-input" />
                <button type="submit" class="popup-new-user_form-submit">Добавить</button>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userName: '',
            lastName: '',
            firstName: '',
            patronymic: '',
            department: '0',
            role: '0',
            password: '',
        };
    },
    methods: {
        handleSubmit() {
            // Доступ к данным из формы
            console.log(this.userName);
            console.log(this.lastName);
            console.log(this.firstName);
            console.log(this.patronymic);
            console.log(this.department);
            console.log(this.role);
            console.log(this.password);

            // Здесь вы можете выполнить необходимые действия с данными, например, отправить их на сервер
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
