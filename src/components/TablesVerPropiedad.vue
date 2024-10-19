<template>
    <div class="tables-container">
        <div>
            <h2>Inventario:</h2>
            <DefaultTable :columns="inventoryColumns" :data="inventoryData" :widthProp="'300px'" />
        </div>

        <div>
            <h2>Trabajadores:</h2>
            <DefaultTable :columns="workersColumns" :data="workersData" :widthProp="'500px'" />
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import DefaultTable from './DefaultTable.vue';

// Example data from db.json for a specific property
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
    { label: 'Nombre', key: 'name' },
    { label: 'Servicio', key: 'service' },
    { label: 'Sueldo', key: 'salary' },
]);
</script>

<style scoped>
.tables-container {
    display: flex;
    flex-direction: row;
    gap: 50px;
    margin: 20px;
}
</style>