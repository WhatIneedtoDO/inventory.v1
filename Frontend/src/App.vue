<script>
    import AuthPage from './components/auth.vue';
    import ActivePage from "./components/active.vue";
    import ComputerPage from './components/computers.vue';
    import AdminPage from "./components/admin.vue";
    import MonitorsPage from "./components/Monitors.vue";
  export default {
      name: "App",
      components: {
          "auth-layout": AuthPage,
          "active-layout": ActivePage,
          "computer-layout": ComputerPage,
          "admin-layout": AdminPage,
          "monitor-layout": MonitorsPage,
      },
      data() {
          return {
              layout: "active-layout",
          };
      },
  };
</script>

<template>
    <div id="app">
        <component :is="$router.layout.name">
            <router-view></router-view>
        </component>
    </div>
</template>

<style>

</style>
