<template>
    <MainLayout>
        <MapaInteractivo class="map" :filtros="filtros"/>
        <div class="filtros">
            <DefaultSelect :options="getNombresPerfiles()" v-model="currentPerfilNombre"/>
            <DefaultSelect :options="disponibilidadOptions" v-model="currentDisponibilidad"/>
        </div>
    </MainLayout>
</template>

<script setup>
    import MainLayout from '@/layouts/MainLayout.vue';
    import MapaInteractivo from '@/components/MapaInteractivo.vue';
    import PerfilService from '@/services/PerfilService';
    import DefaultSelect from '@/components/DefaultSelect.vue';
    import { useUserStore } from '@/stores/user';
    import { computed, onMounted, ref } from 'vue';

    const userStore = useUserStore();
    const userId = userStore.userId;
    const perfilService = new PerfilService();
    const perfiles = ref([]);
    const currentPerfilNombre = ref();
    const disponibilidadOptions = ref(['Disponible', 'No disponible']);
    const currentDisponibilidad = ref('Disponible');

    const getPerfiles = async () => {
        perfiles.value = await perfilService.getPerfilesByUserId(userId);
        currentPerfilNombre.value = perfiles.value[0].nombre;
    }

    const getNombresPerfiles = () => {
        return perfiles.value.map(perfil => perfil.nombre);
    }

    const currentPerfilId = computed(() =>{
        if (!currentPerfilNombre.value) return null;
        return perfiles.value.find(perfil => perfil.nombre === currentPerfilNombre.value).id;
    });

    const filtros = computed(() => {        
        return {
            perfilId: currentPerfilId.value,
            disponibilidad: currentDisponibilidad.value === 'Disponible',
        }
    });
    

    onMounted(() => {
        getPerfiles();
    });
</script>

<style lang="scss" scoped>
    .map {
        margin: 40px auto;
    }
</style>