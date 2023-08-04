<template>
  <div class="page">
    <Navbar />
    <section class="main-page">
      <div
        class="main-page_top"
        style="justify-content: space-between; max-width: 1550px"
      >
        <h2 class="main-page_title">Администрирование</h2>
        <SearchBar />
      </div>
      <button
        class="add-new-user-btn"
        @click="openNewUserPopup = !openNewUserPopup"
      >
        Добавить сотрудника
      </button>
      <Table :users="users" @rowClick="showPopup" />
      <NewUserPopup v-if="openNewUserPopup" @close="closePopup()" />
      <button class="button-exit">Выйти</button>
    </section>
    <section class="search">
      <SearchBar />
    </section>
  </div>
</template>

<script>
import Navbar from "./adminpage/Navbar.vue";
import Table from "./adminpage/Table.vue";
import SearchBar from "./adminpage/SearchBar.vue";
import NewUserPopup from "./adminpage/NewUserPopup.vue";

export default {
  name: "AdminPage",
  layout: "AdminLayout",
  components: {
    Navbar,
    Table,
    SearchBar,
    NewUserPopup,
  },
  data() {
    return {
      selectedUser: null,
      showPopup: false,
      openNewUserPopup: false,
    };
  },
  methods: {
    showPopup(user) {
      this.selectedUser = user;
      this.showPopup = true;
    },
    closePopup() {
      this.showPopup = false;
      this.openNewUserPopup = false;
    },
  },
};
</script>

<style>
/* Стили для .page и других общих стилей */
</style>
