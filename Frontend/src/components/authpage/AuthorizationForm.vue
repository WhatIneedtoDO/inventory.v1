<template>
  <form @submit="submitForm" class="authorization">
    <h1 class="authorization_text">Авторизация</h1>
    <input
      v-model="name"
      type="text"
      id="name_input"
      name="name"
      class="authorization_input"
      placeholder="Учетная запись"
    />
    <input
      v-model="password"
      type="password"
      id="password_input"
      name="password"
      class="authorization_input"
      placeholder="Пароль"
    />
    <p v-if="error" class="authorization_error">Неверный логин или пароль</p>
    <button type="submit" class="authorization_button">Войти</button>
  </form>
</template>

<script>
import {ref} from "vue";
import {useRouter, RouterLink} from "vue-router";
export default {
    components: {
        RouterLink
    },
    setup() {
        const name = ref("");
        const password = ref("");
        const error = ref(false);
        const router = useRouter();

        const ApiAuthorization =
            "http://26.13.163.62:8081/api/v1/auth/authenticate";
        const submitForm = async (e) => {
            e.preventDefault();
            const payload = {
                username: name.value,
                password: password.value,
            };
            try {
                const response = await fetch(ApiAuthorization, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(payload),
                });
                const data = await response.json();
                const jwtToken = data.access_token;
                console.log(jwtToken);
                localStorage.setItem("jwtToken", jwtToken);


                await router.push("/AdminLayout");
            } catch (error) {
                console.error("Error:", error);
            }
            error.value = true;
        };

        return {
            name,
            password,
            error,
            submitForm,
        };
    },
};
</script>

<style src="../../assets/style.css" scoped></style>
