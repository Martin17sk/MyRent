<template>
  <button
    class="button"
    :style="{ width: widthProp, height: heightProp, fontSize: fontSizeProp }"
  >
    <slot></slot>
  </button>
</template>

<script setup>
defineProps({
  widthProp: {
    type: String,
    default: '100px',
  },
  heightProp: {
    type: String,
    default: '50px',
  },
  fontSizeProp: {
    type: String,
    default: '1.375rem',
  },
})
</script>

<style lang="scss" scoped>
@use '../scss/_variables' as *;

.button {
  appearance: none;
  display: flex;
  background-color: $button-color;
  color: $button-text-color;
  padding: 1.69vh 1.25vw;
  font-family: $font;
  font-weight: 50;
  border-radius: 32px;
  border: none;
  align-items: center;
  justify-content: center;

  &:hover {
    background-color: $button-hover-color;
  }

  &:active {
    background-color: $button-active-color;
  }
}
</style>
