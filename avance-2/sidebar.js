const menuButton = document.querySelector('.menu');
const sidebar = document.querySelector('.sidebar');

// Escuchamos el evento click en el botón de menú
menuButton.addEventListener('click', function() {
    // Alternamos la clase 'active' en la sidebar para mostrarla u ocultarla
    sidebar.classList.toggle('active');
});

document.addEventListener('click', function(event) {
    if (!sidebar.contains(event.target) && !menuButton.contains(event.target)) {
        sidebar.classList.remove('active');
    }
});