<template>
    <MainLayout>
        <div class="contenido-container">
            <div class="contador-edit-container">
                <div class="edit-container">
                    <ToggleIconButton :widthProp="'50px'" :heightProp="'50px'" borderRadiusProp="10px"
                        initialImage='basura_icono.svg' otherImage='from_red_delete_icono.svg' v-model="isDefault" />
                </div>
                <div class="contador-container">
                    <DefaultContador :value="propiedades.length" :maxValue="10" :dividerColor="'#ffffff'"
                        :contadorColor="'#303036'" :widthProp="'100px'" :heightProp="'50px'" />
                </div>
            </div>
            <div class="propiedades-botones-container">
                <div class="propiedades-botones-wrapper">
                    <div class="propiedad-boton" v-for="propiedad in propiedades" :key="propiedad.id">
                        <RouterLink :to="'/propiedades/' + propiedad.id">
                            <ProfileButton widthProp="200px" heightProp="200px" image="propiedad-icono.svg"
                                :class="{ 'disable': !isDefault }" />
                        </RouterLink>
                        <div class="delete-propiedad" v-if="!isDefault" @click="clickDeleteButton(propiedad.id)">
                            <ProfileButton widthProp="45px" heightProp="45px" image="basura_icono.svg"
                                borderProp="1px solid rgba(255, 255, 255, 0.5)" />
                        </div>
                        <h3>{{ propiedad.nombre }}</h3>
                    </div>
                    <div :class="['propiedad-boton', 'aniadir-boton', { 'disable': !isDefault }]" v-if="canAddProperty">
                        <RouterLink to="/agregar-propiedad">
                            <ProfileButton widthProp="200px" heightProp="200px" image="añadir-icono.svg" />
                        </RouterLink>
                        <h3>Añadir propiedad</h3>
                    </div>
                </div>
            </div>
            <ConfirmPopup :visible="openPopup" title="¿Estás seguro de que quieres eliminar esta propiedad?"
                @close="openPopup = false" @confirm="deleteProperty">
                <p>Si eliminas esta propiedad, no podrás recuperarla</p>
            </ConfirmPopup>

        </div>
    </MainLayout>
</template>

<script setup>
import MainLayout from '@/layouts/MainLayout.vue';
import ProfileButton from '@/components/ProfileButton.vue';
import PropiedadesService from '@/services/PropiedadesService';
import DefaultContador from '@/components/DefaultContador.vue';
import ToggleIconButton from '@/components/ToggleIconButton.vue';
import ConfirmPopup from '@/components/ConfirmPopup.vue';
import ProfilePicker from '@/components/ProfilePicker.vue';
import { useUserStore } from '@/stores/user';
import { computed, onMounted, ref, watch } from 'vue';
import { RouterLink } from 'vue-router';

const openPopup = ref(false);
const isDefault = ref(true);
const propiedades = ref([]);
const currentDeleteId = ref();

const store = useUserStore();

const servicio = new PropiedadesService();





const clickDeleteButton = (id) => {
    openPopup.value = true;
    currentDeleteId.value = id;
};

const getPropiedades = async () => {
    propiedades.value = await servicio.getPropiedadesByProfile(store.profileId);
};

const canAddProperty = computed(
    () => propiedades.value.length < 10
);

const deleteProperty = async () => {
    await servicio.deletePropiedad(currentDeleteId.value);
    getPropiedades();
};

onMounted(() => {
    getPropiedades();
    console.log(propiedades);
});

watch(store, () => {
    getPropiedades();
});
</script>

<style scoped lang="scss">
.contenido-container {
    display: flex;
    flex-direction: column;
    width: 100%;
    padding-top: 20px;
    margin: auto;

    .contador-edit-container {
        display: flex;
        flex-direction: row;
        position: relative;
        justify-content: space-between;
        align-items: center;
        width: fit-content;
        height: fit-content;
        gap: 20px;
        left: 85%;

        .contador-container {
            display: flex;
            align-items: center;
            width: fit-content;
            height: fit-content;
        }

        .edit-container {
            width: fit-content;
            height: fit-content;
        }
    }



    .propiedades-botones-container {
        display: flex;
        align-items: center;
        height: 700px;


        .propiedades-botones-wrapper {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            align-items: center;
            gap: 20px;
            width: fit-content;
            height: fit-content;
            max-width: 1080px;
            margin: auto;

            .propiedad-boton {
                display: flex;
                flex-direction: column;
                align-items: center;
                position: relative;
                gap: 10px;

                .delete-propiedad {
                    position: absolute;
                    right: -10px;
                    top: -10px;
                }
            }

            .aniadir-boton {
                opacity: 0.5;
            }

        }
    }

    .disable {
        filter: brightness(0.5);
        pointer-events: none;
    }
}
</style>