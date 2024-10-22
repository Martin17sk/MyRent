<template>
    <MainLayout>
        <div class="ver-propiedad-container" v-if="propiedad">
            <div class="imagenes-container">
                <div class="nombre-info-container">
                    <h1>{{ propiedad.nombre }}</h1>
                    <div class="propiedad-img-container">
                        <img :src="urlImgPropiedad" alt="Imagen de la propiedad">
                    </div>
                </div>
                <div class="mapa-direccion-container">
                    <div class="mapa-img-container">
                        <img :src="urlImgMapa" alt="Imagen del mapa">
                        <h3>{{ propiedad.direccion }}</h3>
                    </div>
                </div>
            </div>
            <div class="info-container">
                <div class="info-propiedad-container">
                    <h1>Información</h1>
                    <div class="info-propiedad-detalles">
                        <li>Precio: ${{ propiedad.precio }}</li>
                        <li v-if="propiedad.capacidad > 1">Capacidad: {{ propiedad.capacidad }} personas</li>
                        <li v-else>Capacidad: {{ propiedad.capacidad }} persona</li>
                        <li>Dormitorios: {{ propiedad.dormitorios }}</li>
                        <li>Baños: {{ propiedad.banos }}</li>
                        <li>Dimensiones: {{ propiedad.largo }} x {{ propiedad.ancho }} x
                            {{ propiedad.alto }} {{ propiedad.medida }}</li>
                    </div>
                </div>
                <TablesVerPropiedad :propiedadId="propiedadId"/>
            </div>
        </div>
    </MainLayout>
</template>

<script setup>
import TablesVerPropiedad from '@/components/TablesVerPropiedad.vue';
import MainLayout from '@/layouts/MainLayout.vue';
import PropiedadesService from '@/services/PropiedadesService';
import { useRoute } from 'vue-router';
import { onMounted, ref } from 'vue';

const route = useRoute();
const servicio = new PropiedadesService();
const propiedadId = ref(route.params.id)
const propiedad = ref()

const getPropiedad = async () => {
    propiedad.value = await servicio.getPropiedadById(propiedadId.value);
    
    console.log(propiedad.value);
}

onMounted(() => {
    getPropiedad();
})

const urlImgPropiedad = ref('https://upload.wikimedia.org/wikipedia/commons/c/ca/Machu_Picchu%2C_Peru_%282018%29.jpg');
const urlImgMapa = ref('https://www.cervantesvirtual.com/images/portales/constituciones_hispanoamericanas/graf/mapas/06-mexico_mapa_de_los_estados_s.jpg');
</script>

<style scoped lang="scss">
.ver-propiedad-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 50px;
    width: fit-content;
    margin: 70px auto;

    .imagenes-container {
        display: flex;
        flex-direction: row;
        gap: 50px;

        .nombre-info-container {
            display: flex;
            flex-direction: column;
            gap: 10px;

            .propiedad-img-container {
                display: flex;
                width: 650px;
                height: 450px;

                img {
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                    border-radius: 10px;
                }
            }
        }

        .mapa-direccion-container {
            display: flex;
            flex-direction: column;
            gap: 10px;

            .mapa-img-container {
                display: flex;
                flex-direction: column;
                width: 500px;
                height: 458px;
                padding-top: 58px;

                img {
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                    border-radius: 10px;
                }

            }
        }
    }

    .info-container {
        display: flex;
        flex-direction: row;
        gap: 100px;
        align-items: start;

        .info-propiedad-container {
            display: flex;
            flex-direction: column;
            gap: 10px;

            li {
                list-style-type: none;
                font-size: 16px;
            }
        }
    }

}
</style>