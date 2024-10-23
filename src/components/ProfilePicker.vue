<template>
    <transition name="fade">
        <aside ref="menu" class="picker" v-if="perfiles && visible">
            <SideBarButton v-for="perfil in perfiles" :key="perfil.id" imageName="perfil_icono.svg" @click="selectPerfil(perfil)">
                {{ perfil.nombre }}
            </SideBarButton>
            <SideBarButton imageName="config_icono.svg">Editar</SideBarButton>
        </aside>
    </transition>
</template>

<script setup>
    import PerfilService from '@/services/PerfilService';
    import SideBarButton from './SideBarButton.vue';
    import { useUserStore } from '@/stores/user';
    import { onMounted, ref, watch } from 'vue';

    const props = defineProps({
        visible: Boolean
    })

    const emit = defineEmits(['close']);

    const service = new PerfilService();
    const perfiles = ref([]);
    const userStore = useUserStore();

    const menu = ref(null);

    const getPerfiles = async () => {
        perfiles.value = await service.getPerfilesByUserId(userStore.userId);
    }

    const selectPerfil = (perfil) => {
        userStore.setProfileId(perfil.id);
    }

    const handleClickOutside = (event) => {
        if (menu.value && !menu.value.contains(event.target)) {
            emit('close');
        }
    }

    watch(() => props.visible, (newValue) => {
        if (newValue) {
            setTimeout(() => {
                document.addEventListener('click', handleClickOutside);
            }, 100);
        } else {
            document.removeEventListener('click', handleClickOutside);
        }
    });

    onMounted(() => {
        getPerfiles();
    });
</script>

<style lang="scss" scoped>
    @use '../scss/_variables' as *;

    .fade-enter-active, .fade-leave-active {
        transition: opacity 0.5s;
    }

    .fade-enter-from, .fade-leave-to {
        opacity: 0;
    }

    .fade-enter-to, .fade-leave-from {
        opacity: 1;
    }

    

    .picker {
        position: absolute;
        z-index: 100;
        top: 75px;
        right: 20px;
        display: flex;
        flex-direction: column;
        width: fit-content;
        gap: 10px;
        padding: 10px;
        border-radius: 10px;
        background-color: $button-color;
    }
</style>