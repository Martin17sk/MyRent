const ctx = document.getElementById('myChart').getContext('2d');
let chart; // Variable global para el gráfico
const three_months = ['Ene', 'Feb', 'Mar'];
const six_months = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun'];
const twelve_months = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'];

const three_months_data = [10, 20, 30];
const six_months_data = [10, 20, 30, 40, 50, 60];
const twelve_months_data = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100 , 110, 120];

const months_map = {
    "0" : three_months,
    "1" : six_months,
    "2" : twelve_months
};

const months_data_map = {
    "0" : three_months_data,
    "1" : six_months_data,
    "2" : twelve_months_data
};




// Función para crear el gráfico
function createChart(labels, data, type) {
    if (chart) {
        chart.destroy(); // Destruir gráfico previo para actualizar
    }

    chart = new Chart(ctx, {
        type: type,
        data: {
            labels: labels,
            datasets: [{
                label: 'Datos personalizados',
                data: data,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// Manejar la sumisión del formulario
document.getElementById('chart-form').addEventListener('submit', function(e) {
    e.preventDefault();
    // Obtener los valores del formulario
    const meses = document.getElementById('meses').value;
    const labels = months_map[meses];
    const data = months_data_map[meses];
    const chartType = document.getElementById('chartType').value;

    // Crear el gráfico
    createChart(labels, data, chartType);
});

// Crear gráfico inicial
createChart(['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun'], [10, 20, 30, 40, 50, 60], 'bar');