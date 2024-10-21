<template>
    <div class="tables-container">
        <div>
            <h2>Inventario:</h2>
            <DefaultTable :columns="inventoryColumns" :data="inventoryData" :widthProp="'300px'" />
        </div>

        <div v-if="relacionesEmpleadoPropiedad">
            <h2>Trabajadores:</h2>
            <DefaultTable :columns="workersColumns" :data="empleados" :widthProp="'500px'" />
        </div>
    </div>
</template>

<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import DefaultTable from './DefaultTable.vue';
import EmpleadoService from '@/services/EmpleadoService';

const props = defineProps({
    propiedadId: String,
    required: true,
});

const relacionesEmpleadoPropiedad = ref([]);
const servicio = new EmpleadoService();
const empleados = ref([]);

const getEmpleadoPropiedad = async () => {
    relacionesEmpleadoPropiedad.value = await servicio.getEmpleadosByPropiedadId(props.propiedadId);
}

const getEmpleados = async () => {
    empleados.value = await Promise.all(
        listaIdEmpleados.value.map(async (id) => {
            return await servicio.getEmpleadoById(id);
        })
    );
};

const listaIdEmpleados = computed(() => {
    return relacionesEmpleadoPropiedad.value.map(relacion => relacion.empleado_id);
});

onMounted(() => {
    getEmpleadoPropiedad();
});

watch(listaIdEmpleados, () => {
    getEmpleados();
});


const inventoryData = ref([
    { name: "Sartén", quantity: 2 },
    { name: "Olla", quantity: 1 },
    { name: "Cuchara", quantity: 3 },
    { name: "Tenedor", quantity: 3 },
    { name: "Plato", quantity: 3 },
]);

const workersData = ref([
    { name: "Sebastian", service: "Limpieza", salary: 100 },
]);

const inventoryColumns = ref([
    { label: 'Nombre', key: 'name' },
    { label: 'Cantidad', key: 'quantity' },
]);

const workersColumns = ref([
    { label: 'Nombre', key: 'nombre' },
    { label: 'ID', key: 'id' },
]);
</script>

<style scoped>
.tables-container {
    display: flex;
    flex-direction: row;
    gap: 50px;
}
</style>