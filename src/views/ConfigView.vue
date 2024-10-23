<template>
    <MainLayout>
        <div class="page">
            <h1>Configuracion</h1>
            <RouterLink :to="{ name: 'ConfigCuenta'}">
                <DefaultButton widthProp="400px">Cuenta</DefaultButton>
            </RouterLink>
        </div>
    </MainLayout>
</template>

<script setup>
    import MainLayout from '@/layouts/MainLayout.vue';
    import DefaultButton from '@/components/DefaultButton.vue';
    import { RouterLink } from 'vue-router';
</script>

<style lang="scss" scoped>

    .page {
        padding-top: 100px;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 1rem;
    }
</style>