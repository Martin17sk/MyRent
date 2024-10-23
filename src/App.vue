<script setup>
import { RouterView } from 'vue-router'
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

  a {
    color: inherit;
    text-decoration: none;
  }

  a:visited {
    color: inherit;
  }

  a:hover {
    color: inherit;
  }

  a:active {
    color: inherit;
  }
}
</style>
