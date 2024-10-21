<script setup>
import { RouterView } from 'vue-router';
</script>

<template>
  <div>
    <RouterView />
  </div>
</template>

<style lang="scss">
@use './scss/_variables' as *;

body {
  background-color: $bg-color;
  font-family: $font;
  color: $font-color;
}
</style>
