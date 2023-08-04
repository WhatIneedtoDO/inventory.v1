<template>
    <div class="page-authorization">
        <img src="../static/img/FNS_logo.svg" alt="ФНС" class="logo" />
        <AuthorizationForm />
    </div>
</template>

<script>
import AuthorizationForm from './authpage/AuthorizationForm.vue';
export default {
    name: "AuthPage",
    layout: 'AuthLayout',
    components: {
        AuthorizationForm,
    },
};
</script>

<style src="../assets/style.css" scoped>
/* Ваши стили */
</style>
