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
            <tr v-for="monitor in monitors" :key="monitor.id">
                <td>{{ monitor.id }}</td>
                <td>{{ monitor.serialnumber }}</td>
                <td>{{ monitor.i_number }}</td>
                <td>{{ monitor.production.name }}</td>
                <td>{{ monitor.model.name }}</td>
                <td>{{ monitor.city.name }}</td>
                <td>{{ monitor.location.street + ', ' + monitor.location.number + ', ЕКП: ' + monitor.location.ekp }}</td>
                <td>{{ monitor.room }}</td>
                <td>{{ monitor.comment }}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    data() {
        return {
            monitors: [],
        };
    },
    async created() {
        this.monitors = await this.fetchData();
        this.$emit('pass-value',this.monitors.length);
    },
    methods: {
        async fetchData() {
            const ApiUsers = "http://26.13.163.62:8081/api/v1/Monitors/all";
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
