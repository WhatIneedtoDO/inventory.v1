<template>
  <div class="page">
    <Navbar />
    <section class="main-page">
      <div
        class="main-page_top"
        style="justify-content: space-between; max-width: 1550px"
      >
        <h2 class="main-page_title">Мониторы</h2>
        <SearchBar />
      </div>
      <Filters :totalItems="total" />
      <div class="main-page_form-buttons">
        <button
          type="submit"
          class="main-page_form-btn-submit"
          @click="openNewMonPopup = !openNewMonPopup"
        >
          Добавить
        </button>
      </div>
      <DataTable @pass-value="handleValueFromChild" />
      <NewMonPopup v-if="openNewMonPopup" @close="closePopup()" />
    </section>
    <section class="search">
      <SearchBar />
    </section>
  </div>
</template>

<script>
import Navbar from "./adminpage/Navbar.vue";
import DataTable from "./monitorspage/monitorTable.vue";
import SearchBar from "./adminpage/SearchBar.vue";
import Filters from "./computerspage/Filters.vue";
import NewMonPopup from "./monitorspage/NewMonPopup.vue";
import NewCompPopup from "./computerspage/NewCompPopup.vue";

export default {
  name: "monitorsPage",
  layout: "monitorsLayout",
  components: {
    NewCompPopup,
    Filters,
    SearchBar,
    Navbar,
    DataTable,
    NewMonPopup,
  },
  data() {
    return {
      childValue: "",
      total: "",
      openNewMonPopup: false,
    };
  },
  methods: {
    handleValueFromChild(value) {
      this.childValue = value;
      this.total = this.childValue; // Update the value from the child
    },
    closePopup() {
      this.openNewMonPopup = false;
    },
  },
};
</script>

<style scoped></style>