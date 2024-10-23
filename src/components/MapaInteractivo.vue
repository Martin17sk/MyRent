<template>
    <div id="map" class="map-container"></div>
</template>

<script setup>
    import PropiedadesService from "@/services/PropiedadesService";
    import mapboxgl from "mapbox-gl";
    import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
    import { useRouter } from "vue-router";
    import { onMounted, watch } from "vue";
    import 'mapbox-gl/dist/mapbox-gl.css';
    import '@mapbox/mapbox-gl-geocoder/dist/mapbox-gl-geocoder.css';

    const props = defineProps({
        filtros: Object
    });

    const router = useRouter();

    const propiedadService = new PropiedadesService();
    let propiedades = [];

    let marks = [];
    let currentMarker = null;

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
        
        marks.forEach((mark) => {
            mark.remove()
        });
        marks = [];
    }

    function getPreviewPropiedad(propiedad) {
        return `
            <div class="popup">
                <h3>${propiedad.nombre}</h3>
                <p>${propiedad.direccion}</p>
            </div>
        `;
    }

    function loadMarks() {
        if (!propiedades) return;
        
        propiedades.forEach((propiedad) => {
            const popup = new mapboxgl.Popup({
                closeButton: false,
            }).setHTML(getPreviewPropiedad(propiedad));


            const mark = new mapboxgl.Marker()
                .setLngLat([propiedad.longitud, propiedad.latitud])
                .setPopup(popup)
                .addTo(map);
            marks.push(mark);

            mark.getElement().style.cursor = 'pointer';
            
            mark.getElement().addEventListener('click', () => {
                router.push(`/propiedades/${propiedad.id}`);
            });

            mark.getElement().addEventListener('mouseenter', () => {
                popup.setLngLat(mark.getLngLat()).addTo(map);
                popup.getElement().addEventListener('click', () => {
                    console.log("click");
                    
                    router.push(`/propiedades/${propiedad.id}`);
                });
            });

            mark.getElement().addEventListener('mouseleave', () => {
                popup.remove();
            });
        });
    }

    const getPropiedades = async () => {
        propiedades = await propiedadService.getPropiedadesByProfile(props.filtros.perfilId);
        propiedades = propiedades.filter(propiedad => propiedad.disponibilidad === props.filtros.disponibilidad);
    };

    function update() {
        getPropiedades().then(() => {
            removeMarks();
            loadMarks();
        });
    }

    function addMarker(e) {
        if (currentMarker) {
            currentMarker.remove();
        }
        const coords = e.lngLat;

        const popupHTML = `
            <div class="popup">
                Agregar propiedad aquí
            </div>
        `;
        

        let popup = new mapboxgl.Popup({closeButton: false}).setHTML(popupHTML);
        currentMarker = new mapboxgl.Marker({
            draggable: true 
            }).setLngLat([coords.lng, coords.lat])
            .setPopup(popup)
            .addTo(map);
        
        popup.addTo(map);

        popup.getElement().addEventListener('click', () => {
            console.log("click");
            
            router.push('/agregar-propiedad');
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
        map.on('dblclick', addMarker);
    });
</script>

<style lang="scss">
    @use "../scss/_variables" as *;

    .map-container {
        width: 80%;
        height: 80vh;
        border-radius: 10px;
        overflow: hidden;
        border: 1px solid $divider-color;
    }
    .popup {
        color: white;
        background-color: $button-color;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid $divider-color;
        &:hover {
            cursor: pointer;
            background-color: $button-hover-color;
        }

        &:active {
            background-color: $button-active-color;
        }
    }

    .mapboxgl-popup-content {
        background-color: transparent;
        padding: 0;
    }

    .mapboxgl-popup-tip {
        display: none;
    }
</style>