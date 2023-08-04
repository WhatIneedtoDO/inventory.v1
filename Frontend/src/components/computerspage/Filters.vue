<template>
  <form class="main-page_form">
    <div class="main-page_form-info">
      <div class="main-page_form-lots">
        <p class="main-page_form-lot">Всего :</p>
        <p class="main-page_form-lot-amount">{{ totalItems }}</p>
        <p class="main-page_form-lot">Исправные :</p>
        <p class="main-page_form-lot-amount">0</p>
      </div>
      <div class="main-page_form-sort">
        <p class="main-page_form-sort-text">сортировать по :</p>

        <select class="main-page_form-select">
          <option class="main-page_form-option" selected value="0">-</option>
          <option class="main-page_form-option" value="1">Возрастанию</option>
          <option class="main-page_form-option" value="2">Убыванию</option>
        </select>
      </div>
    </div>

    <div class="main-page_form-params">
      <div class="main-page_form-date-container">
        <p class="main-page_form-input-text">от</p>
        <input
          type="date"
          name="starDate"
          id="1"
          class="main-page_form-input"
        />
        <p class="main-page_form-input-text">до</p>
        <input type="date" name="endDate" id="" class="main-page_form-input" />
      </div>
      <div class="main-page_form-filters-container">
        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 1</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>
        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 2</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>

        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 3</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>

        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 4</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>
      </div>

      <div class="main-page_form-filters-container">
        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 5</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>

        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 6</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>

        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 7</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>

        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 8</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>
      </div>

      <div class="main-page_form-filters-container">
        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 9</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>

        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 10</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>

        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 11</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>

        <div class="main-page_form-input-container">
          <p class="main-page_form-input-text">Фильтер 12</p>
          <select class="main-page_form-input">
            <option class="main-page_form-option" selected value="0">-</option>
            <option class="main-page_form-option" value="1">Возрастанию</option>
            <option class="main-page_form-option" value="2">Убыванию</option>
          </select>
        </div>
      </div>
    </div>

    <div class="main-page_form-buttons">
      <button type="reset" class="main-page_form-btn-reset">Сброс</button>
      <button class="main-page_form-btn-search">Поиск</button>
    </div>
  </form>
</template>

<script>
export default {
  name: "Filters",
  props: {
    totalItems: Number, // Declare the prop
  },
};
</script>

<style scoped></style>
