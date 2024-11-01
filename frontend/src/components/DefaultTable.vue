<template>
  <div class="table-container">
    <table :style="{ width: widthProp }">
      <thead>
        <tr>
          <th v-for="(column, index) in columns" :key="index">
            {{ column.label }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(row, rowIndex) in data" :key="rowIndex">
          <td v-for="(column, colIndex) in columns" :key="colIndex">
            {{ row[column.key] }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
const props = defineProps({
  columns: {
    type: Array,
    required: true,
  },
  data: {
    type: Array,
    required: true,
  },
  widthProp: {
    type: String,
    default: '600px',
  },
})
</script>

<style scoped lang="scss">
.table-container {
  table {
    border-radius: 7px;
    border-collapse: separate;
    border-spacing: 0;

    th,
    td {
      border: #ffffff40 1px solid;
      padding: 8px;
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
}
</style>
