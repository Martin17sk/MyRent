<template>
  <div>
    <Line :data="data" :options="options" />
  </div>
</template>

<script setup>
import { Line } from 'vue-chartjs'
import { Chart, registerables } from 'chart.js'
import { ref } from 'vue'
Chart.register(...registerables)
defineProps({
  data: Object,
})

const options = ref({
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    y: {
      beginAtZero: true,
    },
  },
})
</script>

<style lang="scss" scoped>
div {
  width: 700px;
  height: 500px;
}
</style>
