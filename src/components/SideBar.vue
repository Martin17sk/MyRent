<template>
    <transition name="slide">
        <aside ref="sidebar" v-if="isOpen" class="sidebar-container">
            <div class="sidebar-contenido">
                <div class="sidebar-opciones">
                    <RouterLink :to="{ name: 'Propiedades' }">
                        <SideBarButton imageName="casa_simple_icono.svg" >Inicio</SideBarButton>
                    </RouterLink>
                    <RouterLink :to="{ name: 'Home' }">
                        <SideBarButton imageName="informes_icono.svg">Informes</SideBarButton>
                    </RouterLink>
                    <RouterLink :to="{ name: 'Comparar' }">
                        <SideBarButton imageName="comparar_icono.svg">Comparar</SideBarButton>
                    </RouterLink>
                    <RouterLink :to="{ name: 'Graficos' }">
                        <SideBarButton imageName="graficos_icono.svg">Graficos</SideBarButton>
                    </RouterLink>
                    <RouterLink :to="{ name: 'Mapa' }">
                        <SideBarButton imageName="mapa_sidebar_icono.svg">Mapa</SideBarButton>
                    </RouterLink>
                    <RouterLink :to="{ name: 'Home' }">
                        <SideBarButton imageName="calendario_sidebar_icono.svg">Calendario</SideBarButton>
                    </RouterLink>
                </div>
                <div class="divider"></div>
                <div class="sidebar-opciones">
                    <RouterLink v-for="propiedad in propiedades" :key="propiedad.id" :to="{ name: 'Propiedad', params: { id: propiedad.id }}">
                        <SideBarButton imageName="casa_simple_icono.svg">{{propiedad.nombre}}</SideBarButton>
                    </RouterLink>
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

<script setup>
    import SideBarButton from '@/components/SideBarButton.vue';
    import PropiedadesService from '@/services/PropiedadesService';
    import { useUserStore } from '@/stores/user';
    import { RouterLink } from 'vue-router';
    import { watch, ref, onMounted } from 'vue';
    
    const perfilId = useUserStore().profileId;
    const propiedadesService = new PropiedadesService();
    const propiedades = ref([]);
    const emit = defineEmits(['close']);
    const props = defineProps({
        isOpen: {
            type: Boolean,
            default: false
        }
    });

    const getPropiedades = async () => {
        propiedades.value = await propiedadesService.getPropiedadesByProfile(perfilId);
    }

    const sidebar = ref(null);

    const closeSidebar = () => {
        emit('close');
    }

    const handleClickOutside = (event) => {
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

    onMounted(() => {
        getPropiedades();
    });
</script>

<style scoped lang="scss">
    @use '../scss/_variables' as *;
    $sidebar-width: 200px;

    .slide-enter-active, .slide-leave-active {
        transition: all 0.3s ease;
    }

    .slide-enter-from {
        transform: translateX(-100%);
    }

    .slide-enter-to {
        transform: translateX(0);
    }

    .slide-leave-from {
        transform: translateX(0);
    }

    .slide-leave-to {
        transform: translateX(-100%);
    }

    a {
        color: inherit; /* Usa el color de texto que tengas por defecto */
        text-decoration: none; /* Elimina el subrayado */
    }

    a:visited {
        color: inherit; /* Para que los enlaces visitados tengan el mismo color */
    }

    a:hover {
        color: inherit; /* Aplica un color más claro cuando se hace hover (opcional) */
    }

    a:active {
        color: inherit; /* Aplica el color de cuando el enlace está activo */
    }



    .sidebar-container {
        top: 0;
        z-index: 100;
        position: fixed;
        padding: 50px 25px; 
        color: white;
        width: $sidebar-width;
        height: 100vh;
        background-color: $secondary-color;
        border-right: 1px solid rgba(255, 255, 255, 0.25);
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    @media (max-width: 800px) {
        .sidebar-container {
            width: 70px;
            padding: 50px 10px;
            align-items: center;
        }
        
        .sidebar-contenido {
            width: fit-content;
        }
        .sidebar-opciones{
            width: fit-content;
        }
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