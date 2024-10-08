<template>
    <transition name="slide">
        <aside ref="sidebar" v-if="isOpen" class="sidebar-container">
            <div class="sidebar-contenido">
                <div class="sidebar-opciones">
                    <SideBarButton imageName="casa_simple_icono.svg">Inicio</SideBarButton>
                    <SideBarButton imageName="informes_icono.svg">Informes</SideBarButton>
                    <SideBarButton imageName="comparar_icono.svg">Comparar</SideBarButton>
                    <SideBarButton imageName="graficos_icono.svg">Graficos</SideBarButton>
                    <SideBarButton imageName="mapa_sidebar_icono.svg">Mapa</SideBarButton>
                    <SideBarButton imageName="calendario_sidebar_icono.svg">Calendario</SideBarButton>
                </div>
                <div class="divider"></div>
                <div class="sidebar-opciones">
                    <SideBarButton imageName="casa_simple_icono.svg">Propiedad 1</SideBarButton>
                    <SideBarButton imageName="casa_simple_icono.svg">Propiedad 2</SideBarButton>
                    <SideBarButton imageName="casa_simple_icono.svg">Propiedad 3</SideBarButton>
                    <SideBarButton imageName="casa_simple_icono.svg">Propiedad 4</SideBarButton>
                </div>
                <div class="divider"></div>
                <div class="sidebar-opciones">
                    <SideBarButton imageName="config_icono.svg">Configuracion</SideBarButton>
                </div>
            </div>
            <div class="sidebar-logout">
                <SideBarButton imageName="log_out_icono.svg">Cerrar Sesion</SideBarButton>
            </div>     
        </aside>
    </transition>
    
</template>

<script setup lang="ts">
    import SideBarButton from '@/components/SideBarButton.vue';
    import { defineProps, defineEmits, watch, ref, Ref} from 'vue';

    const emit = defineEmits(['close']);
    const props = defineProps({
        isOpen: {
            type: Boolean,
            default: false
        }
    });

    const sidebar : Ref = ref(null);

    const closeSidebar = () => {
        emit('close');
    }

    const handleClickOutside = (event: MouseEvent) => {
        if (sidebar.value && !sidebar.value.contains(event.target)) {
            closeSidebar();
        }
    }

    watch(() => props.isOpen, (newValue) => {
        if (newValue) {
            setTimeout(() => {
                document.addEventListener('click', handleClickOutside);
            }, 100);
        } else {
            document.removeEventListener('click', handleClickOutside);
        }
    });
</script>

<style lang="scss" scoped>

    $sidebar-width: 150px;

    .slide-enter-active, .slide-leave-active {
        transition: transform 0.3s ease;
    }

    .slide-enter-from, .slide-leave-to {
        transform: translateX(-100%);
    }

    .slide-enter-to, .slide-leave-from {
        transform: translateX(0);
    }

    .sidebar-container {
        position: fixed;
        padding: 50px 25px; 
        color: white;
        width: $sidebar-width;
        height: 89.44vh;
        background-color: $secondary-color;
        border-right: 1px solid rgba(255, 255, 255, 0.25);
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .sidebar-contenido {
        display: flex;
        flex-direction: column;
        gap: 25px;
    }

    .sidebar-opciones {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

    .divider {
        width: 100%;
        height: 1px;    
        background-color: $divider-color;
    }
</style>