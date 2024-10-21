<template>
    <div id="map" class="map-container"></div>
</template>

<script setup>
    import PropiedadesService from "@/services/PropiedadesService";
    import mapboxgl from "mapbox-gl";
    import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
    import { onMounted, watch } from "vue";
    import 'mapbox-gl/dist/mapbox-gl.css';
    import '@mapbox/mapbox-gl-geocoder/dist/mapbox-gl-geocoder.css';

    const props = defineProps({
        filtros: Object
    });

    const propiedadService = new PropiedadesService();
    let propiedades = [];

    let marks = [];

    let map;
    mapboxgl.accessToken = import.meta.env.VITE_MAPBOX_TOKEN;

    function loadMap() {
        map = new mapboxgl.Map({
            container: "map", 
            style: "mapbox://styles/sadddge/cm0wwdbqx00fi01nt5aazaoaz", 
            center: [-72.6056, -38.7397], // Coordenadas de inicio [longitud, latitud]
            zoom: 12, // Nivel de zoom inicial
        });
    }

    function loadMapOptions() {
        map.on('load', () => {
            const geocoder = new MapboxGeocoder({
                accessToken: mapboxgl.accessToken,
                mapboxgl: mapboxgl,
            });
            map.addControl(geocoder,'top-left');
            map.addControl(new mapboxgl.FullscreenControl(), 'bottom-right');
            map.addControl(new mapboxgl.NavigationControl(), 'bottom-right');
            map.doubleClickZoom.disable();
        });
    }

    function removeMarks() {
        if (!marks) return;
        console.log("remove init length", marks.length);
        
        marks.forEach((mark) => {
            mark.remove()
            console.log('mark removed');
        });
        marks = [];
    }

    function loadMarks() {
        if (!propiedades) return;
        console.log("loadMarks", propiedades);
        
        propiedades.forEach((propiedad) => {
            const mark = new mapboxgl.Marker()
                .setLngLat([propiedad.longitud, propiedad.latitud])
                .addTo(map);
            marks.push(mark);
        });
        console.log("marks", marks);
    }

    const getPropiedades = async () => {
        propiedades = await propiedadService.getPropiedadesByProfile(props.filtros.perfilId);
        propiedades = propiedades.filter(propiedad => propiedad.disponibilidad === props.filtros.disponibilidad);
        console.log(propiedades);
        console.log(props.filtros);
    };

    function update() {
        getPropiedades().then(() => {
            removeMarks();
            loadMarks();
        });
    }


    watch(() => props.filtros, async () => {
        update();
    });

    onMounted(() => {
        loadMap();
        loadMapOptions();
        map.on('load', () => {
            update();
        });
    });
</script>

<style scoped lang="scss">
    @use "../scss/_variables" as *;

    .map-container {
        width: 80%;
        height: 700px;
        border-radius: 10px;
        overflow: hidden;
        border: 1px solid $divider-color;
    }
</style>