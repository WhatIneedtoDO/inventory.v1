<template>
  <div class="table-container">
    <table class="main-page_table" id="admin-table">
      <tbody>
        <tr>
          <th>ID</th>
          <th>Учетная запись</th>
          <th>ФИО</th>
          <th>Должность</th>
          <th>Группа</th>
          <th>Отдел</th>
        </tr>
        <tr v-for="user in users" :key="user.id" @click="openPopup(user)">
          <td>{{ user.username }}</td>
          <td>{{ user.firstname }}</td>
          <td>{{ user.lastname }}</td>
          <td>{{ user.password }}</td>
          <td>{{ user.role }}</td>
        </tr>
      </tbody>
    </table>
    <PopupUser
      :selectedUser="selectedUser"
      v-if="showPopup"
      @close="closePopup"
      :users="users"
      @save="saveChangesToTable"
    />
  </div>
</template>

<script>
import PopupUser from "./PopupUser.vue";
import { ref } from "vue";

export default {
  components: {
    PopupUser,
  },
  data() {
    return {
      users: [], // Пустой массив для хранения пользователей
      selectedUser: null,
      showPopup: false,
    };
  },
  async created() {
    this.users = await this.fetchData();
  },
  methods: {
    async fetchData() {
      const ApiUsers = "http://26.13.163.62:8081/api/v1/admin/Users";
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
    openPopup(user) {
      this.selectedUser = user;
      this.showPopup = true;
    },
    closePopup() {
      this.showPopup = false;
    },
    saveChangesToTable(updatedUser) {
      // Find the index of the updatedUser in the users array
      const index = this.users.findIndex((user) => user.id === updatedUser.id);
      if (index !== -1) {
        // Update the user data at the found index
        this.users.splice(index, 1, updatedUser);
      }

      // Close the popup
      this.showPopup = false;
    },
  },
};
</script>

<style scoped>
/* Стили для .table-container и других общих стилей */
</style>
