<template>
    <table>
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Dirección</th>
                <th>Capacidad</th>
                <th>Dormitorios</th>
                <th>Banos</th>
                <th>Disponibilidad</th>
                <th>Precio</th>
                <th>Ingresos</th>
                <th>Gastos</th>
                <th>Costes</th>
                <th>Beneficio</th>
            </tr>
        </thead>
        <tbody>
            <tr v-if="propiedad1 && desempenio1">
                <td>{{ propiedad1.nombre }}</td>
                <td>{{ propiedad1.direccion }}</td>
                <td>{{ propiedad1.capacidad }}</td>
                <td>{{ propiedad1.dormitorios }}</td>
                <td>{{ propiedad1.banos }}</td>
                <td>{{ propiedad1.disponibilidad }}</td> 
                <td>{{ propiedad1.precio }}</td>
                <td>{{ desempenio1.ingresos }}</td>
                <td>{{ desempenio1.gastos }}</td> 
                <td>{{ desempenio1.costes }}</td> 
                <td>{{ desempenio1.beneficio_neto }}</td>
            </tr>
            <tr v-else>
                <td v-for="n in 11">-</td>
            </tr>
            <tr v-if="propiedad2 && desempenio2">
                <td>{{ propiedad2.nombre }}</td>
                <td>{{ propiedad2.direccion }}</td>
                <td>{{ propiedad2.capacidad }}</td>
                <td>{{ propiedad2.dormitorios }}</td>
                <td>{{ propiedad2.banos }}</td>
                <td>{{ propiedad2.disponibilidad }}</td> 
                <td>{{ propiedad2.precio }}</td>
                <td>{{ desempenio2.ingresos }}</td>
                <td>{{ desempenio2.gastos }}</td> 
                <td>{{ desempenio2.costes }}</td> 
                <td>{{ desempenio2.beneficio_neto }}</td>
            </tr>
            <tr v-else>
                <td v-for="n in 11">-</td>
            </tr>
        </tbody>
    </table>

</template>

<script setup>
    import FinancialService from '@/services/FinancialService';
    import { ref, watch } from 'vue';

    const props = defineProps({
        propiedad1: {
            type: Object,
            required: true
        },
        propiedad2: {
            type: Object,
            required: true
        },
        mes1: {
            type: Number,
            required: true
        },
        mes2: Number
    })

    const financialService = new FinancialService();
    const desempenio1 = ref({});
    const desempenio2 = ref({});

    const getDesempeniosByPropiedadIdAndMes = async () => {
        desempenio1.value = await financialService.getDesempenioByPropiedadIdAndMes(props.propiedad1.id, props.mes1);
        desempenio2.value = await financialService.getDesempenioByPropiedadIdAndMes(props.propiedad2.id, props.mes2);
    }

    watch(() => props, () => {        
        getDesempeniosByPropiedadIdAndMes();
    }, { deep: true });
</script>

<style lang="scss" scoped>
    table {
        border-radius: 7px;
        border-collapse: separate;
        border-spacing: 0;

        th,
        td {
            border: #ffffff40 1px solid;
            padding: 8px 15px;
            text-align: left;
        }

        th {
            background-color: #3a3a3f;
            color: white;
            text-align: center;
        }

        tr:first-child th:first-child {
            border-top-left-radius: 7px;
        }

        tr:first-child th:last-child {
            border-top-right-radius: 7px;
        }

        tr:last-child td:first-child {
            border-bottom-left-radius: 7px;
        }

        tr:last-child td:last-child {
            border-bottom-right-radius: 7px;
        }
    }
</style>