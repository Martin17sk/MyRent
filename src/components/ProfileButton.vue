<template>
    <div class="button">
        <img src="../assets/images/perfil_icono.svg" alt="">
    </div>
</template>

<script setup>
</script>

<style lang="scss" scoped>
    .button {
        display: flex;
        width: 50px;
        height: 50px;
        border-radius: 30px;
        background-color: $button-color;
        align-items: center;
        justify-content: center;

        &:hover {
            background-color: $button-hover-color;
        }

        &:active {
            background-color: $button-active-color;
        }

        img {
            width: 30px;
            height: 30px;
        }
    }
</style>