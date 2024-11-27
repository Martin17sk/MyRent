<template>
  <div class="contenido">
    <div class="info-contenedor">
      <div @click="toggleEditMode" class="info-contenedor__edit-icon">
        <svg
          id="icono-editar"
          width="40"
          height="40"
          viewBox="0 0 40 40"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill-rule="evenodd"
            clip-rule="evenodd"
            d="M21.4821 11.8512L25.5049 7.82837C26.8382 6.49504 27.5049 5.82837 28.3333 5.82837C29.1617 5.82837 29.8284 6.49504 31.1617 7.82837L32.1716 8.83818C33.5049 10.1715 34.1716 10.8382 34.1716 11.6666C34.1716 12.495 33.5049 13.1617 32.1716 14.495L28.1851 18.4815C25.4167 16.8714 23.1123 14.5842 21.4821 11.8512ZM20.0278 13.3054L7.52303 25.8102C7.09797 26.2353 6.88544 26.4478 6.74571 26.7089C6.60597 26.97 6.54703 27.2647 6.42914 27.8542L5.14707 34.2645C5.08055 34.5971 5.04729 34.7634 5.1419 34.858C5.2365 34.9526 5.4028 34.9194 5.73538 34.8529L5.7354 34.8529L5.73542 34.8529L12.1457 33.5708C12.7352 33.4529 13.0299 33.394 13.291 33.2542C13.5521 33.1145 13.7646 32.902 14.1897 32.4769L26.7271 19.9395C24.0094 18.2652 21.7194 15.9912 20.0278 13.3054Z"
            fill="white"
            fill-opacity="0.6"
          />
        </svg>
      </div>
      <div class="info-contenedor__contador-perfiles">
        <DefaultContador
          v-if="perfs"
          max-value="5"
          :value="perfs.length"
          contadorColor="#303036"
          dividerColor="white"
        ></DefaultContador>
      </div>
    </div>
    <div class="contenedor-img-perfiles">
      <div
        style="display: flex; flex-direction: column; align-items: center"
        v-for="(perfil, index) in perfs"
        :key="index"
      >
        <RouterLink :to="{ name: 'Propiedades' }">
          <ProfileButton
            widthProp="140px"
            heightProp="140px"
            border-radius-prop="200px"
            @click="store.setProfileId(perfil.id)"
          >
          </ProfileButton>
        </RouterLink>
        <span style="padding: 10px 0">{{ perfil.nombre }}</span>
        <Transition name="fade">
          <button v-if="editMode" class="eliminar-button">Eliminar</button>
        </Transition>
      </div>
      <div
        @click="toggleModal"
        style="display: flex; flex-direction: column; align-items: center"
      >
        <ProfileButton
          widthProp="140px"
          heightProp="140px"
          image="añadir-icono.svg"
        ></ProfileButton>
        <span style="padding: 10px 0">Añadir perfil</span>
      </div>
    </div>
    <ConfirmPopup
      class="modalPerfil"
      title="Crear perfil"
      :visible="showModal"
      @close="toggleModal"
      @confirm="null"
    >
      <label for="nombrePerf">Nombre de perfil</label>
      <input
        autocomplete="false"
        v-model="inputValue"
        class="input-text"
        type="text"
        id="nombrePerf"
        name="nombrePerf"
        placeholder="Ingrese un nombre descriptivo"
      />
      <label for="empleadoPerf">Empleado</label>
      <div
        v-for="(empleado, index) in empleados"
        :key="index"
        class="empleado-input"
      >
        <input
          v-model="empleados[index]"
          class="input-text"
          type="text"
          :id="'empleadoPerf' + index"
          :name="'empleadoPerf' + index"
          placeholder="Deje en blanco si desea agregar más tarde"
        />
        <button type="button" @click="removeEmpleado(index)">Eliminar</button>
      </div>
      <button class="agregar" type="button" @click="addEmpleado">Agregar Empleado</button>
    </ConfirmPopup>
    <VCalendar v-if="showCalendar" class="calendar" />
    <!-- Modal para crear perfiles -->
  </div>
</template>

<script setup>
import DefaultContador from '@/components/DefaultContador.vue'
import ProfileButton from '@/components/ProfileButton.vue'
import { RouterLink } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { useAuthStore } from '@/stores/auth'
import { onMounted, ref } from 'vue'
import ConfirmPopup from '@/components/ConfirmPopup.vue'
import PerfilService from '@/services/PerfilService'
import router from '@/router'
const editMode = ref(false)
const showModal = ref(false)
const inputValue = ref('')
const empleados = ref([])
const perfs = ref([])
const perfilService = new PerfilService();

const addEmpleado = () => {
  empleados.value.push('');
};

const removeEmpleado = (index) => {
  empleados.value.splice(index, 1);
};


const toggleEditMode = () => {
  editMode.value = !editMode.value
}

const toggleModal = () => {
  showModal.value = !showModal.value
}

const userStore = useUserStore()
const authStore = useAuthStore() // Lo debería usar para comprobaciones de autenticación


const cargarPerfiles = async ()=>{
  perfs.value = await perfilService.getPerfilesByUserId(userStore.userId)
}

onMounted(async () => {
  await authStore.loadUserFromLocalStorage()
  await authStore.validateSession()
  if (authStore.isAuthenticated) {
    console.log('Usuario autenticado: ' + userStore.user + " cargando perfiles")
    perfs.value = await perfilService.getPerfilesByUserId(userStore.userId)
  } else {
    router.push('/login')
  }
})
</script>

<style lang="scss">
.modalPerfil{
  top: auto;
  overflow-y: auto;
  max-height: 100vh;
}

.agregar{
    background-color: #303036;
    border-radius: 12px;
    color: white;
    border: none;
    padding: 20px 10px;
    cursor: pointer;
    &:hover{
        background-color: #5c5c68;
    }
    &:active{
    background-color: #79798a;

    }
}

.empleado-input {
  display: flex;
  align-items: center;
  gap: 10px;
  
  margin-bottom: 10px;
  button{
    background-color: #303036;
    border-radius: 12px;
    color: white;
    border: none;
    padding: 20px 10px;
    cursor: pointer;
  }
  button:hover{
    background-color: #5c5c68;
  }
  button:active{
    background-color: #79798a;
  }
}

.input {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.input-text {
  padding: 20px 24px;
  color: #ffffff;
  background-color: #303036;
  border-radius: 12px;
  border: 1px solid #66666659;
}

.input-text:focus {
  border: 1px solid rgb(20, 174, 92);
  outline: none;
}

.fade-enter-active,
.fade-leave-active {
  transition: all 0.1s ease-in-out;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
.fade-enter-to,
.fade-leave-from {
  opacity: 1;
}

.eliminar-button {
  border: none;
  background-color: #303036;
  color: white;

  width: 8vw !important;
  height: 4vh !important;
  border-radius: 10px !important;
  cursor: pointer;
  &:hover {
    background-color: #4d4d56;
  }
  &:active {
    background-color: #6c6c79;
  }
}

.calendar {
  position: absolute;
  bottom: 0;
  left: 0;
  margin: 20px;
}

.contenido {
  width: 100%;
  height: fit-content;
}

.info-contenedor {
  display: flex;
  flex-direction: row;
  gap: 2vh;
  justify-content: right;
  margin-top: 0;
  margin-right: 0;
  padding-top: 4.5vh;
  padding-right: 6vh;
}

.info-contenedor__contador-perfiles {
  display: flex;
  align-items: center;
  border-radius: 10px;
  background-color: #303036;
  user-select: none;
}

.info-contenedor__contador-perfiles h2 {
  display: flex;
  align-items: center;
  font-weight: 700;
}

.contador-perfiles__num-der {
  margin-left: 2vh;
  margin-right: 1.5vh;
}

.contador-perfiles__num-izq {
  margin-left: 1.5vh;
  margin-right: 2vh;
}

.contador-perfiles__divisor-vertical {
  display: flex;
  align-items: center;
  width: 1px;
  height: 3vh;
  margin-top: 1vh;
  margin-bottom: 1vh;
  background-color: #ffffff;
}
.info-contenedor__edit-icon {
  display: flex;
  cursor: pointer;
  align-items: center;
}
#icono-editar {
  transition: all 0.3s ease-in-out;
}

#icono-editar:hover {
  transform: scale(1.2);
  fill: #ffffff;
}

/* perfiles */

.contenedor-img-perfiles {
  display: flex;
  justify-content: center;
  width: auto;
  margin: 30vh;
  gap: 3vh;
  text-align: center;
}

.contenedor-img-perfiles h3 {
  display: flex;
  justify-content: center;
  margin-top: 1vh;
}

.contenedor-img-perfiles button {
  width: 20vh;
  height: 20vh;
  padding: 0px;
  align-items: center;
  border-radius: 100%;
}

.contenedor-img-perfiles img {
  display: flex;
  margin: auto;
}

#perfil-quote {
  position: absolute;
  top: 14%;
  left: 50%;
  transform: translateX(-50%);
  z-index: 1;
  font-size: 2rem;
  transition: font-size 0.3s ease;
}

#perfil-quote:hover {
  font-size: 2.2rem;
}

.contenedor-img-perfiles__agregar-perfil {
  opacity: 50%;
}

.perfil-boton {
  position: relative;
}

.perfil-boton__alerta {
  position: absolute;
  top: 0.5vh;
  right: 0.5vh;
}
</style>
