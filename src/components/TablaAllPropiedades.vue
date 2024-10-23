<template>
    <div>
        <DefaultTable :data="data" :columns="columns" widthProp="100%"/>
    </div>
</template>

<script setup>
    import DefaultTable from './DefaultTable.vue';
    import PropiedadesService from '@/services/PropiedadesService';
    import FinancialService from '@/services/FinancialService';
    import { ref, onMounted, computed } from 'vue';

    const propiedadesService = new PropiedadesService();
    const financialService = new FinancialService();
    const propiedades = ref();
    const desempenios = ref();

    const columns = [
        {
            label: 'Nombre',
            key: 'nombre'
        },
        {
            label: 'Dirección',
            key: 'direccion'
        },
        {
            label: 'Capacidad',
            key: 'capacidad'
        },
        {
            label: 'Dormitorios',
            key: 'dormitorios'
        },
        {
            label: 'Baños',
            key: 'banos'
        },
        {
            label: 'Disponibilidad',
            key: 'disponibilidad'
        },
        {
            label: 'Precio',
            key: 'precio'
        },
        {
            label: 'Ingresos',
            key: 'ingresos'
        },
        {
            label: 'Gastos',
            key: 'gastos'
        },
        {
            label: 'Costes',
            key: 'costes'
        },
        {
            label: 'Beneficio',
            key: 'beneficio_neto'
        }
    ]

    const data = computed(() => {
        return propiedades.value ? propiedades.value.map(propiedad => {
            const desempenio = desempenios.value.find(desempenio => desempenio.propiedad_id == propiedad.id);   
            
            return {
                nombre: propiedad.nombre,
                direccion: propiedad.direccion,
                capacidad: propiedad.capacidad,
                dormitorios: propiedad.dormitorios,
                banos: propiedad.banos,
                disponibilidad: propiedad.disponibilidad,
                precio: "$" + propiedad.precio,
                ingresos: desempenio ? "$" + desempenio.ingresos : '-',
                gastos: desempenio ? "$" + desempenio.gastos : '-',
                costes: desempenio ? "$" + desempenio.costes : '-',
                beneficio_neto: desempenio ? "$" + desempenio.beneficio_neto : '-'
            }
        }) : [];
    })

    const getPropiedades = async () => {
        propiedades.value = await propiedadesService.getPropiedades();
    }

    const getDesempenios = async () => {
        desempenios.value = await financialService.getDesempeniosUltimoMes();
    }

    onMounted(() => {
        getDesempenios().then(() => {
            getPropiedades();
        })
    })
</script>

<style scoped>
    div {
        width: 100%;
        margin: 0 auto;
    }
</style>