<template>
    <MainLayout>
        <div class="page">
            <h1>Cuenta</h1>
            <div v-if="user">
                <h3>{{ user.correo }}</h3>
                <h3>{{ user.contraseña }}</h3>
            </div>
        </div>
    </MainLayout>
</template>

<script setup>
    import MainLayout from '@/layouts/MainLayout.vue';
    import { RouterLink } from 'vue-router';
    import { useUserStore } from '@/stores/user';
    import UsuarioService from '@/services/UsuarioService';
    import { onMounted, ref } from 'vue';

    const userStore = useUserStore();
    const usuarioService = new UsuarioService();
    const userId = userStore.userId;
    const user = ref({});

    const getUser = async () => {
        user.value = await usuarioService.getUsuarioById(userId);
    }

    onMounted(() => {
        getUser().then(() => {
            console.log(user.value);
        });
    });
</script>

<style lang="scss" scoped>

</style>