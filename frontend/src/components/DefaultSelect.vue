<template>
  <div class="select-container">
    <label v-if="label" for="select" class="select-label">{{ label }}</label>
    <select
      v-model="selected"
      :disabled="disabled"
      id="select"
      class="custom-select"
    >
      <option v-for="option in options" :key="option.value">
        {{ option }}
      </option>
    </select>
  </div>
</template>

<script setup>
const selected = defineModel()
defineProps({
  label: {
    type: String,
    default: 'Selecciona una opción',
  },
  options: Array,
  disabled: Boolean,
})
</script>

<style lang="scss" scoped>
@use '../scss/_variables' as *;

.select-container {
  display: flex;
  flex-direction: column;
  gap: 8px;
  width: 100%;
  max-width: 200px;
}

.select-label {
  font-size: 14px;
}

.custom-select {
  width: 100%;
  padding: 10px;
  border: 2px solid $input-border-color;
  border-radius: 4px;
  font-size: 16px;
  color: $font-color;
  background-color: $button-color;
  padding-right: 30px;
  outline: none;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  transition: border-color 0.3s ease;
  background-image: url('data:image/svg+xml;charset=UTF-8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-chevron-down"><polyline points="6 9 12 15 18 9"></polyline></svg>');
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 16px;
}

.custom-select:focus {
  border-color: $input-border-focus-color;
}

.custom-select:disabled {
  opacity: 0.5;
}
</style>
