<template>
    <component :is="component" :data="data"/>
</template>

<script setup>
import BarChart from './BarChart.vue';
import LineChart from './LineChart.vue';
import PieChart from './PieChart.vue';
import { computed } from 'vue';

    const props = defineProps({
        currentComponent: {
            type: String,
            default: 'Bar',
        },
        data: Object,
    });

    const component = computed(() => {
        switch (props.currentComponent) {
            case 'Bar':
                return BarChart;
            case 'Line':
                return LineChart;
            case 'Pie':
                return PieChart;
        }
    });
</script>

<style lang="scss" scoped>

</style>