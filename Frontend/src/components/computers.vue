<template>
  <div class="page">
    <Navbar />
    <section class="main-page">
      <div
        class="main-page_top"
        style="justify-content: space-between; max-width: 1550px"
      >
        <h2 class="main-page_title">Компьютеры</h2>
        <SearchBar />
      </div>
      <Filters :totalItems="total" />
      <div class="main-page_form-buttons">
        <button
          type="submit"
          class="main-page_form-btn-submit"
          @click="openNewCompPopup = !openNewCompPopup"
        >
          Добавить
        </button>
      </div>
      <DataTable @pass-value="handleValueFromChild" />
      <NewCompPopup v-if="openNewCompPopup" @close="closePopup()" />
    </section>
    <section class="search">
      <SearchBar />
    </section>
  </div>
</template>

<script>
import Navbar from "./adminpage/Navbar.vue";
import DataTable from "./computerspage/ComputersTable.vue";
import SearchBar from "./adminpage/SearchBar.vue";
import Filters from "./computerspage/Filters.vue";
import NewCompPopup from "./computerspage/NewCompPopup.vue";

export default {
  name: "computersPage",
  layout: "computersLayout",
  components: {
    Filters,
    SearchBar,
    Navbar,
    DataTable,
    NewCompPopup,
  },
  data() {
    return {
      childValue: "",
      total: "",
      openNewCompPopup: false,
    };
  },
  methods: {
    handleValueFromChild(value) {
      this.childValue = value;
      this.total = this.childValue; // Update the value from the child
    },
    closePopup() {
      this.openNewCompPopup = false;
    },
  },
};
</script>

<style scoped></style>
