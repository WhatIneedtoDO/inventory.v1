<template>
    <div class="popup-user popup-active">
        <div class="popup-user-container">
            <button class="popup_close" @click="closePopup">X</button>

            <form action="" class="popup-user_form">
                <h2 style="text-align: center">Изменение данных сотрудника</h2>
                <div class="popup-user_form-inputs-container">
                    <div class="popup-user_form-input">
                        <label for="username">ФИО</label>
                        <input type="text" id="username" v-model="username" />
                    </div>
                    <div class="popup-user_form-input">
                        <label for="firstname">Должность</label>
                        <input type="text" id="firstname" v-model="firstname" />
                    </div>
                </div>

                <div class="popup-user_form-inputs-container">
                    <div class="popup-user_form-input">
                        <label for="role">Группа</label>
                        <input type="text" id="role" v-model="role" />
                    </div>
                    <div class="popup-user_form-input">
                        <label for="lastname">Фамилия</label>
                        <input type="text" id="lastname" v-model="lastname" />
                    </div>
                </div>

                <div class="popup-user_btn-container">
                    <button class="popup-user_btn-delete">Удалить сотрудника</button>
                    <button type="submit" class="popup-user_btn-submit" @click="saveChanges">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        selectedUser: {
            type: Object,
            required: true,
        },
    },
    data() {
        return {
            username: '',
            firstname: '',
            lastname: '',
            password: '',
            role: '',
        };
    },
    watch: {
        selectedUser: {
            immediate: true,
            handler(newVal) {
                if (newVal) {
                    this.username = newVal.username;
                    this.firstname = newVal.firstname;
                    this.lastname = newVal.lastname;
                    this.password = newVal.password;
                    this.role = newVal.role;
                }
            },
        },
    },
    methods: {
        closePopup() {
            this.$emit('close');
        },
        saveChanges() {
            // Assuming you have updated the selectedUser data within the form
            console.log(this.selectedUser);
            this.$emit('save', this.selectedUser);
        },
    },
};
</script>
