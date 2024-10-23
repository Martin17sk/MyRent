<template>
    <header>
        <div class="header-container">
            <IconButton image="Menu.svg" @click="openSideBar"/>
            <h1>MyRent</h1>
            <ProfileButton image="perfil_icono.svg" @click="openPerfiles"/>
        </div>
        <hr>
    </header>
</template>

<script setup>
    import IconButton from './IconButton.vue';
    import ProfileButton from './ProfileButton.vue';
    const emit = defineEmits(['openSideBar', 'openPerfiles']);
    const openSideBar = () => {
        emit('openSideBar');
    }

    const openPerfiles = () => {
        emit('openPerfiles');
    }
</script>

<style lang="scss" scoped>
    @use '../scss/_variables' as *;
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
