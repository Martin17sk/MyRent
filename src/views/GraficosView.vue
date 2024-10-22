<template>
    <MainLayout>
        <div class="content">
            <div class="form">
                <h1>Graficos</h1>
                <div class="opciones">
                    <div class="opcion">
                        <p>Ver por propiedad</p>
                        <DefaultSwitch v-model="isChecked" />
                    </div>
                    <DefaultSelect :options="options" v-model="currentComponent" label="Selecciona un gráfico" />
                    <DefaultSelect :options="getNombresPerfiles()" v-model="currentPerfilNombre"
                        label="Selecciona un perfil" />
                    <DefaultSelect :options="getNombresPerfiles()" :disabled="!isChecked" v-model="currentPerfilNombre"
                        label="Selecciona una propiedad" />
                    <DefaultSelect :options="monthOptions" v-model="currentMonthOption" label="Ultimos" />
                    <DefaultSelect :options="dataOptions" v-model="currentDataOption" label="Datos de" />
                </div>
            </div>
            <DynamicChart :currentComponent="currentComponent" :data="graphData" />
        </div>
    </MainLayout>
</template>

<script setup>
import MainLayout from '@/layouts/MainLayout.vue';
import DefaultSelect from '@/components/DefaultSelect.vue';
import DynamicChart from '@/components/DynamicChart.vue';
import DefaultSwitch from '@/components/DefaultSwitch.vue';
import FinancialService from '@/services/FinancialService';
import PerfilService from '@/services/PerfilService';
import { computed, onMounted, ref, watch } from 'vue';

const service = new FinancialService();
const perfilService = new PerfilService();
const isChecked = ref(false);
const perfiles = ref([]);
const desempenios = ref([]);
const currentComponent = ref('Bar');
const currentPerfilNombre = ref(null);
const currentMonth = new Date().getMonth();
const monthOptions = ['3 Meses', '6 Meses', '1 Año'];
const currentMonthOption = ref('3 Meses');
const options = ref(['Bar', 'Line', 'Pie']);
const dataOptions = ref(['Ingresos', 'Gastos', 'Costes', 'Beneficios']);
const currentDataOption = ref('Ingresos');

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

const monthIndex = (currentMonth, minus) => {
    if (currentMonth - minus < 0) {
        return 12 + (currentMonth - minus);
    }
    return currentMonth - minus;
}

const labelMonthMap = {
    '3 Meses': [monthIndex(currentMonth, 3), monthIndex(currentMonth, 2), monthIndex(currentMonth, 1)].map(month => monthMap[month]),
    '6 Meses': [monthIndex(currentMonth, 6), monthIndex(currentMonth, 5), monthIndex(currentMonth, 4), monthIndex(currentMonth, 3), monthIndex(currentMonth, 2), monthIndex(currentMonth, 1)].map(month => monthMap[month]),
    '1 Año': [monthIndex(currentMonth, 12), monthIndex(currentMonth, 11), monthIndex(currentMonth, 10), monthIndex(currentMonth, 9), monthIndex(currentMonth, 8), monthIndex(currentMonth, 7), monthIndex(currentMonth, 6), monthIndex(currentMonth, 5), monthIndex(currentMonth, 4), monthIndex(currentMonth, 3), monthIndex(currentMonth, 2), monthIndex(currentMonth, 1)].map(month => monthMap[month]),
}

const graphLabel = computed(() => {
    return labelMonthMap[currentMonthOption.value];
});

const dataMaph = computed(() => {
    return {
        'Ingresos': desempenios.value.map(desempenio => desempenio.ingresos),
        'Gastos': desempenios.value.map(desempenio => desempenio.gastos),
        'Costes': desempenios.value.map(desempenio => desempenio.costes),
        'Beneficios': desempenios.value.map(desempenio => desempenio.beneficio_neto),
    }
});

const currentData = computed(() => {
    if (!desempenios.value.length) return [];

    return dataMaph.value[currentDataOption.value];
});

const currentPerfilId = computed(() => {
    if (!currentPerfilNombre.value) return null;
    return perfiles.value.find(perfil => perfil.nombre === currentPerfilNombre.value).id;
});

const getNombresPerfiles = () => {
    return perfiles.value.map(perfil => perfil.nombre);
}

const loadDesempenios = async () => {
    const data = await service.getDesempeniosByPerfilId(currentPerfilId.value);
    const sortedDesempenios = data.sort((a, b) => {
        return new Date(a.fecha) - new Date(b.fecha);
    });

    const desempeniosPorMes = {};

    // Recorremos los desempeños para crear un objeto donde la clave es el mes y el valor es el desempeño
    sortedDesempenios.forEach(desempenio => {
        const mes = new Date(desempenio.fecha).getMonth();  // Obtener el mes del desempeño
        desempeniosPorMes[mes] = desempenio;  // Asignar el desempeño al mes correspondiente
    });

    // Creamos la lista final de desempeños basados en los labels del gráfico
    desempenios.value = graphLabel.value.map(label => {
        const mes = Object.keys(monthMap).find(key => monthMap[key] === label);
        // Si existe un desempeño para ese mes, lo agregamos, si no, agregamos 0
        return desempeniosPorMes[mes] || { ingresos: 0, gastos: 0, costes: 0, beneficios: 0 };
    });
}
const getPerfiles = async () => {
    perfiles.value = await perfilService.getPerfilesByUserId(1);
}
const graphData = computed(() => {
    return {
        labels: graphLabel.value,
        datasets: [
            {
                label: 'Desempeño',
                data: currentData.value,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                ],
                borderColor: [
                    '#14AE5C',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                ],
                borderWidth: 1,
            },
        ],
    }
});

function resetComponent() {
    const value = currentComponent.value;
    currentComponent.value = null;
    setTimeout(() => {
        currentComponent.value = value;
    }, 0);
}

onMounted(() => {
    getPerfiles();
});

watch([graphLabel, currentPerfilNombre], () => {
    loadDesempenios();
}, { deep: true });

</script>

<style lang="scss">
.content {
    display: flex;
    width: fit-content;
    margin: 150px auto;
    flex-direction: row;
    align-items: center;
    gap: 60px;

    .opciones {
        display: flex;
        flex-direction: column;
        gap: 16px;
    }
}

.opcion {
    display: flex;
    width: fit-content;
    align-items: center;
    gap: 16px;
}
</style>