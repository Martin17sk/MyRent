<template>
  <div
    class="button"
    :style="{
      width: widthProp,
      height: heightProp,
      border: borderProp,
      borderRadius: borderRadiusProp,
    }"
  >
    <img
      :src="getImage(image)"
      :style="{
        width: widthNumber * 0.6 + 'px',
        height: heightNumber * 0.6 + 'px',
      }"
    />
  </div>
</template>

<script setup>
const prop = defineProps({
  widthProp: {
    type: String,
    default: '50px',
  },
  heightProp: {
    type: String,
    default: '50px',
  },
  image: {
    type: String,
    default: 'perfil_icono.svg',
  },
  borderProp: {
    type: String,
    default: 'none',
  },
  borderRadiusProp: {
    type: String,
    default: '30px',
  },
})
const widthNumber = parseFloat(prop.widthProp)
const heightNumber = parseFloat(prop.heightProp)

const getImage = imageName => {
  try {
    return new URL(`../assets/images/${imageName}`, import.meta.url).href
  } catch (error) {
    console.error(`Image ${imageName} not found`)
    return ''
  }
}
</script>

<style lang="scss" scoped>
@use '../scss/_variables' as *;

.button {
  display: flex;
  background-color: $button-color;
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
