<template>
  <nav class="nav">
    <a class="nav_logo" href="/">
      <img
        src="../../static/img/inv.svg"
        alt="Инвентаризация"
        class="nav_logo-img"
      />
      <h3 class="nav_logo-text">Инвентаризация</h3>
    </a>
    <div class="nav_links">
      <a href="/Inspections" class="nav_link">
        <img alt="" src="../../static/img/inspec.svg" class="nav_link-img" />
        <h4 class="nav_link-text">Инспекция</h4>
      </a>
      <a href="/Workstations" class="nav_link">
        <img alt="" src="../../static/img/inspec.svg" class="nav_link-img" />
        <h4 class="nav_link-text">Рабочие станции</h4>
      </a>
        <router-link to="/ComputersLayout" class="nav_link">
        <img alt="" src="../../static/img/inspec.svg" class="nav_link-img" />
        <h4 class="nav_link-text">Компьютеры</h4>
        </router-link>
        <router-link to="/MonitorsLayout" class="nav_link">
        <img alt="" src="../../static/img/inspec.svg" class="nav_link-img" />
        <h4 class="nav_link-text">Мониторы</h4>
        </router-link>
      <a href="/Servers" class="nav_link">
        <img alt="" src="../../static/img/inspec.svg" class="nav_link-img" />
        <h4 class="nav_link-text">Серверное оборудование</h4>
      </a>
      <a href="/IBP" class="nav_link">
        <img alt="" src="../../static/img/inspec.svg" class="nav_link-img" />
        <h4 class="nav_link-text">ИБП</h4>
      </a>
      <a href="/Telephones" class="nav_link">
        <img alt="" src="../../static/img/inspec.svg" class="nav_link-img" />
        <h4 class="nav_link-text">Телефония</h4>
      </a>
    </div>
    <div class="nav_user">
      <img src="../../static/img/user.jpg" alt="ava" class="nav_user-img" />
      <div class="nav_user-container">
        <p class="nav_user-info">ФИО</p>
        <p class="nav_user-info">Должность</p>
        <p class="nav_user-info">Группа пользователя</p>
      </div>
    </div>
  </nav>
</template>

<script>
import { RouterLink } from "vue-router";
export default {
  components:{
      RouterLink,
  },
};
</script>

<style>
/* Стили для .nav и других общих стилей */
</style>