<template>
    <header>
        <div class="header-container">
            <IconButton image="Menu.svg" @click="openSideBar"/>
            <h1>MyRent</h1>
            <ProfileButton/>
        </div>
        <hr>
    </header>
</template>

<script setup lang="ts">
    import IconButton from './IconButton.vue';
    import ProfileButton from './ProfileButton.vue';
    import { defineEmits } from 'vue';
    const emit = defineEmits(['openSideBar']);
    const openSideBar = () => {
        emit('openSideBar');
    }
</script>

<style lang="scss" scoped>
    header {
        background-color: $secondary-color;
        color: $font-color;
        display: flex;
        height: 70px;
        hr {
            width: 100%;
            margin-top: 70px;
            left: 0;
            position: absolute;
            color: $divider-color;
        }
    }

    .header-container {
        width: 100%;
        padding: 0 30px;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
    }
</style>
