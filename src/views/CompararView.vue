<template>
    <MainLayout>
        <div class="content">
            <h1>Comparar</h1>
            <div class="opciones">
                <div class="selects">
                    <DefaultSelect :options="ultimos12Meses" v-model="currentMonth1Nombre" label="Selecciona un mes" />
                    <DefaultSelect :options="getPropiedadesNombre" v-model="currentPropiedad1Nombre" label="Selecciona una propiedad" />
                </div>
                <h2><-></h2>
                <div class="selects">
                    <DefaultSelect :options="getPropiedadesNombre" v-model="currentPropiedad2Nombre" label="Selecciona una propiedad" />
                    <DefaultSelect :options="ultimos12Meses" v-model="currentMonth2Nombre" label="Selecciona un mes" />
                </div>
            </div>
            <CompararTabla :propiedad1="currentPropiedad1" :propiedad2="currentPropiedad2" :mes1="currentMonth1" :mes2="currentMonth2" class="tabla"/>
        </div>
        <div>{{ ultimos12Meses }}</div>
    </MainLayout>
</template>

<script setup>
    import MainLayout from '@/layouts/MainLayout.vue';
    import CompararTabla from '@/components/CompararTabla.vue';
    import PropiedadesService from '@/services/PropiedadesService';
    import DefaultSelect from '@/components/DefaultSelect.vue';
    import { computed, onMounted, ref } from 'vue';

    const propiedadesService = new PropiedadesService();
    const propiedades = ref(null);
    const currentMonth = new Date().getMonth();
    const monthMap = {
        0: 'Enero',
        1: 'Febrero',
        2: 'Marzo',
        3: 'Abril',
        4: 'Mayo',
        5: 'Junio',
        6: 'Julio',
        7: 'Agosto',
        8: 'Septiembre',
        9: 'Octubre',
        10: 'Noviembre',
        11: 'Diciembre',
    }

    const reverseMonthMap = Object.fromEntries(Object.entries(monthMap).map(([key, value]) => [value, key]));
    const ultimos12Meses = ref(Array.from({ length: 12 }, (_, i) => monthMap[(currentMonth + i) % 12]));
    
    const currentPropiedad1Nombre = ref();
    const currentPropiedad2Nombre = ref();
    const currentMonth1Nombre = ref();
    const currentMonth2Nombre = ref();

    const currentMonth1 = computed(() => {
        return currentMonth1Nombre.value ? reverseMonthMap[currentMonth1Nombre.value] : null;
    })

    const currentMonth2 = computed(() => {
        return currentMonth2Nombre.value ? reverseMonthMap[currentMonth2Nombre.value] : null;
    })

    const currentPropiedad1 = computed(() => {
        return propiedades.value ? propiedades.value.find(propiedad => propiedad.nombre === currentPropiedad1Nombre.value) : null;
    })
    const currentPropiedad2 = computed(() => {
        return propiedades.value ? propiedades.value.find(propiedad => propiedad.nombre === currentPropiedad2Nombre.value) : null;
    })
    
    const getPropiedades = async () => {
        propiedades.value = await propiedadesService.getPropiedades();        
    }

    const getPropiedadesNombre = computed(() => {
        return propiedades.value ? propiedades.value.map(propiedad => propiedad.nombre) : [];
    })

    onMounted(() => {
        getPropiedades()
    })
</script>

<style lang="scss" scoped>
    .content {
        display: flex;
        flex-direction: column;

        

        .opciones {
            display: flex;
            flex-direction: row;
            width: fit-content;
            align-items: center;
            gap: 20px;
            margin-bottom: 20px;
            .selects {
                display: flex;
                flex-direction: row;
                width: 400px;
                align-items:end;
                gap: 20px;
            }

            h2 {
                margin-top: 25px;
                white-space: nowrap;
            }
        }
    }
    .tabla {
        margin: auto;
    }
</style>