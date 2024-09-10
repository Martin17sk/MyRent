// Sustituye 'your-access-token' con tu token de acceso de Mapbox
mapboxgl.accessToken = 'pk.eyJ1Ijoic2FkZGRnZSIsImEiOiJjbHltOWE4ejUxY21lMmtvY2s3ZG5iN2JiIn0.UtJHjn2nuEqAbHkLSOh4pA';
var map = new mapboxgl.Map({
    container: 'map', // ID del div
    style: 'mapbox://styles/sadddge/cm0wwdbqx00fi01nt5aazaoaz', // Estilo del mapa
    center: [-74.5, 40], // Longitud, Latitud (en este caso, Nueva York)
    zoom: 9 // Nivel de zoom
});

// Añadir un marcador al mapa
var marker = new mapboxgl.Marker()
    .setLngLat([-74.5, 40])
    .addTo(map);