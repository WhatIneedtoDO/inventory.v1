<template>
    <div class="table-container">
        <table class="main-page_table">
            <tbody>
            <tr>
                <th>ID</th>
                <th>Серийный номер</th>
                <th>Инвентарный номер</th>
                <th>Производитель</th>
                <th>Модель</th>
                <th>Город</th>
                <th>Местонахождение</th>
                <th>Комната</th>
                <th>Комментарий</th>
            </tr>
            <tr v-for="comp in comps" :key="comp.id">
                <td>{{ comp.id }}</td>
                <td>{{ comp.serialnumber }}</td>
                <td>{{ comp.i_number }}</td>
                <td>{{ comp.production.name }}</td>
                <td>{{ comp.model.name }}</td>
                <td>{{ comp.city.name }}</td>
                <td>{{ comp.location.street + ', ' + comp.location.number + ', ЕКП: ' + comp.location.ekp }}</td>
                <td>{{ comp.room }}</td>
                <td>{{ comp.comment }}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    data() {
        return {
            comps: [],
        };
    },
    async created() {
        this.comps = await this.fetchData();
        this.$emit('pass-value',this.comps.length);
    },
    methods: {
        async fetchData() {
            const ApiUsers = "http://26.13.163.62:8081/api/v1/Computers/all";
            const token = localStorage.getItem("jwtToken");
            const requestOptions = {
                method: "GET",
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json", // Adjust the content type as needed
                },
            };
            try {
                const response = await fetch(ApiUsers, requestOptions);
                return response.json();
            } catch (error) {
                console.error("Error fetching data:", error);
                return []; // Возвращаем пустой массив в случае ошибки
            }
        },
    }
};
</script>
