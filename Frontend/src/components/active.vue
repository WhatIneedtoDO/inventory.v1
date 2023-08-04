<template>
  <div class="page">
    <Navbar />
    <SearchBar/>
  </div>
</template>

<script>
import { ref } from "vue";
import { useRoute, RouterLink } from "vue-router";
import Navbar from "./adminpage/Navbar.vue";
import SearchBar from "./adminpage/SearchBar.vue";

export default {
  name: "ActivePage",
    layout: 'ActiveLayout',
  components: {
      SearchBar,
      Navbar,
    RouterLink,
  },
  setup() {
    const name = ref("");

    // Используйте useRoute() для получения текущего маршрута и параметров
    const route = useRoute();
    if (route.params.name) {
      name.value = route.params.name;
    }

    return {
      name,
    };
  },
};
</script>

<style scoped>
</style>
