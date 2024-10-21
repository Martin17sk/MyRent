<template>
    <MainLayout>
        <div class="ver-propiedad-container">
            <div class="imagenes-container">
                <div class="nombre-info-container">
                    <h1>{{ nombrePropiedad }}</h1>
                    <div class="propiedad-img-container">
                        <img :src="urlImgPropiedad" alt="Imagen de la propiedad">
                    </div>
                </div>
                <div class="mapa-direccion-container">
                    <div class="mapa-img-container">
                        <img :src="urlImgMapa" alt="Imagen del mapa">
                        <h3>{{ direccionPropiedad }}</h3>
                    </div>
                </div>
            </div>
            <div class="info-container">
                <div class="info-propiedad-container">
                    <h1>Información</h1>
                    <div class="info-propiedad-detalles">
                        <li>Precio: ${{ precioPropiedad }}</li>
                        <li v-if="capacidadPropiedad > 1">Capacidad: {{ capacidadPropiedad }} personas</li>
                        <li v-else>Capacidad: {{ capacidadPropiedad }} persona</li>
                        <li>Dormitorios: {{ dormitoriosPropiedad }}</li>
                        <li>Baños: {{ banosPropiedad }}</li>
                        <li>Dimensiones: {{ dimensionesPropiedad.largo }} x {{ dimensionesPropiedad.ancho }} x
                            {{ dimensionesPropiedad.alto }} {{ dimensionesPropiedad.unidad }}</li>
                    </div>
                </div>
                <TablesVerPropiedad />
            </div>
        </div>
    </MainLayout>
</template>

<script setup>
import TablesVerPropiedad from '@/components/TablesVerPropiedad.vue';
import MainLayout from '@/layouts/MainLayout.vue';
import { ref } from 'vue';

const nombrePropiedad = ref('Nombre de la propiedad');
const urlImgPropiedad = ref('https://upload.wikimedia.org/wikipedia/commons/c/ca/Machu_Picchu%2C_Peru_%282018%29.jpg');
const urlImgMapa = ref('https://www.cervantesvirtual.com/images/portales/constituciones_hispanoamericanas/graf/mapas/06-mexico_mapa_de_los_estados_s.jpg');
const direccionPropiedad = ref('Dirección de la propiedad');
const precioPropiedad = 10000
const capacidadPropiedad = 3
const dormitoriosPropiedad = 2
const banosPropiedad = 2
const dimensionesPropiedad = {
    largo: 10,
    ancho: 5,
    alto: 3,
    unidad: 'm'
}
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