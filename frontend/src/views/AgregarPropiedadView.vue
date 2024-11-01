<template>
  <MainLayout>
    <form class="form" @submit.prevent="submitForm">
      <div class="left-side">
        <h1>Información del lugar</h1>
        <div class="inputs">
          <InputLabelVModel
            v-model="form.name"
            label="Nombre"
            placeholder="ej.: Cabaña Luxury"
            widthProp="100%"
            heightProp="200px"
            isObligatory="true"
          />
          <InputLabelVModel
            v-model="form.direccion"
            label="Dirección"
            placeholder="ej.: Vicente Salazar 143"
            widthProp="100%"
            heightProp="200px"
            isObligatory="true"
          />
        </div>
        <div class="numeric-services">
          <div class="numeric-info">
            <InputLabelVModel
              v-model="form.capacidad"
              label="Capacidad"
              placeholder="ej.: 4"
              widthProp="150px"
              heightProp="200px"
              isObligatory="true"
            />
            <InputLabelVModel
              v-model="form.dormitorios"
              label="Dormitorios"
              placeholder="ej.: 2"
              isObligatory="true"
            />
            <InputLabelVModel
              v-model="form.banos"
              label="Baños"
              placeholder="ej.: 1"
              isObligatory="true"
            />
            <InputLabelVModel
              v-model="form.precio"
              label="Precio de renta"
              placeholder="ej.: 50000"
              isObligatory="true"
            />
          </div>
          <div class="services-checks">
            <div class="amenities">
              <div class="column">
                <DefaultCheckbox
                  labelText="Internet"
                  v-model="services.internet"
                />
                <DefaultCheckbox labelText="TV" v-model="services.tv_cable" />
                <DefaultCheckbox
                  labelText="Microondas"
                  v-model="services.microondas"
                />
              </div>
              <div class="column">
                <DefaultCheckbox
                  labelText="Refrigerador"
                  v-model="services.refrigerador"
                />
                <DefaultCheckbox
                  labelText="Lavadora"
                  v-model="services.lavadora"
                />
                <DefaultCheckbox
                  labelText="Secadora"
                  v-model="services.secadora"
                />
              </div>
            </div>
            <div class="divider-hor"></div>
            <div class="services">
              <div class="column">
                <DefaultCheckbox
                  labelText="Servicio de comida"
                  v-model="services.servicio_de_comida"
                />
                <DefaultCheckbox
                  labelText="Servicio de limpieza"
                  v-model="services.servicio_de_limpieza"
                />
                <DefaultCheckbox
                  labelText="Permitir mascotas"
                  v-model="services.permitir_mascotas"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="divider-ver"></div>
      <div class="right-side">
        <h1>Otros</h1>
        <div class="dimensions">
          <div class="dimensions-top">
            <div class="dimensions-title">
              <h2>Dimensiones</h2>
              <h2 class="optional-text">(opcional)</h2>
            </div>
            <div class="dimensions-measures">
              <DefaultSelect
                label=""
                :options="measures"
                v-model="currentMeasure"
              />
            </div>
          </div>
          <div class="dimensions-inputs">
            <InputLabelVModel
              v-model="form.largo"
              label="Largo"
              placeholder="ej.: 10"
              widthProp="50px"
            />
            <InputLabelVModel
              v-model="form.ancho"
              label="Ancho"
              placeholder="ej.: 10"
              widthProp="50px"
            />
            <InputLabelVModel
              v-model="form.alto"
              label="Alto"
              placeholder="ej.: 10"
              widthProp="50px"
            />
          </div>
        </div>
        <div class="inventory">
          <div class="inventory-top">
            <div class="inventory-title">
              <h2>Inventario</h2>
              <h2 class="optional-text">(opcional)</h2>
            </div>
            <div class="add-inventory-button">
              <ImageLabelButton
                imageName="añadir-icono.svg"
                label="Agregar objeto"
                widthImageProp="40px"
                heightImageProp="40px"
                class="add-button"
                @click="clickAddInventoryButton"
              />
            </div>
          </div>
          <div class="inventory-objects">
            <ul>
              <li v-for="object in listInventoryObjects">
                {{ object.name }} (x{{ object.cantidad }})
              </li>
            </ul>
          </div>
        </div>
        <div class="employee">
          <div class="employee-top">
            <div class="employee-title">
              <h2>Empleados</h2>
              <h2 class="optional-text">(opcional)</h2>
            </div>
            <div class="add-employee-button">
              <ImageLabelButton
                imageName="añadir-icono.svg"
                label="Agregar empleado"
                widthImageProp="40px"
                heightImageProp="40px"
                class="add-button"
                @click="clickAddEmployeeButton"
              />
            </div>
          </div>
          <ul>
            <li v-for="employee in listEmployees">
              {{ employee }}
            </li>
          </ul>
        </div>
        <div class="image">
          <div class="image-title">
            <h2>Imágenes</h2>
            <h2 class="optional-text">(opcional)</h2>
          </div>
          <div class="add-image-button">
            <UploadImage
              widthProp="600px"
              heightProp="200px"
              label="Subir imágen"
            >
              <img src="@/assets/images/image-icono.svg" alt="" />
            </UploadImage>
          </div>
        </div>

        <div class="send-form-buttons">
          <DefaultButton
            widthProp="110px"
            heightProp="40px"
            fontSizeProp="16px"
            class="confirm-button"
            type="submit"
          >
            Confirmar
          </DefaultButton>
          <RouterLink to="/propiedades">
            <DefaultButton
              widthProp="110px"
              heightProp="40px"
              fontSizeProp="16px"
              class="cancel-button"
            >
              Cancelar
            </DefaultButton>
          </RouterLink>
        </div>
      </div>
      <ConfirmPopup
        :visible="openInventoryPopup"
        title="Agregar objeto"
        @close="openInventoryPopup = false"
        @confirm="addObject(inventoryObject)"
      >
        <InputLabelVModel
          label="Nombre"
          :placeholder="'ej.: Tenedor'"
          v-model="inventoryObject.name"
        />
        <InputLabelVModel
          label="Cantidad"
          :placeholder="'ej.: 3'"
          v-model="inventoryObject.cantidad"
        />
      </ConfirmPopup>

      <ConfirmPopup
        :visible="openEmployeePopup"
        title="Seleccione un empleado"
        @close="openEmployeePopup = false"
        @confirm="addEmployee(selectedEmployee)"
      >
        <DefaultSelect
          label="Empleados"
          :options="getEmployeesNames"
          v-model="selectedEmployee"
        />
      </ConfirmPopup>
    </form>
  </MainLayout>
</template>

<script setup>
import { computed, onMounted, reactive, ref } from 'vue'
import InputLabelVModel from '@/components/InputLabelVModel.vue'
import MainLayout from '@/layouts/MainLayout.vue'
import DefaultButton from '@/components/DefaultButton.vue'
import DefaultCheckbox from '@/components/DefaultCheckbox.vue'
import DefaultSelect from '@/components/DefaultSelect.vue'
import ImageLabelButton from '@/components/ImageLabelButton.vue'
import UploadImage from '@/components/UploadImage.vue'
import ConfirmPopup from '@/components/ConfirmPopup.vue'
import EmpleadoService from '@/services/EmpleadoService'
import PropiedadesService from '@/services/PropiedadesService'
import { useAuthStore } from '@/stores/auth'
import { useUserStore } from '@/stores/user'
import ObjetoService from '@/services/ObjetoService'
import { useRouter, RouterLink } from 'vue-router'

const props = defineProps({
  propiedad: Object,
})

const store = useUserStore()
const authStore = useAuthStore()
const router = useRouter()
const empleadoService = new EmpleadoService()
const propiedadesService = new PropiedadesService()
const objetoService = new ObjetoService()

const employees = ref([])
const selectedEmployee = ref('')

const listInventoryObjects = ref([])
const openInventoryPopup = ref(false)

const listEmployees = ref([])
const openEmployeePopup = ref(false)

const currentMeasure = ref('m')

const measures = ['cm', 'm', 'in', 'ft']

const getEmployees = async () => {
  employees.value = await empleadoService.getEmpleadosByProfileId(
    store.profileId,
  )
}

const getEmployeeByName = nombre => {
  return employees.value.find(employee => employee.nombre === nombre)
}

const getEmployeesNames = computed(() => {
  let names = employees.value
    .map(employee => employee.nombre)
    .filter(employee => !listEmployees.value.includes(employee))

  if (names.length === 0) {
    selectedEmployee.value = ''
  }
  return names
})

const inventoryObject = reactive({
  name: '',
  cantidad: 0,
})

const form = props.propiedad
  ? reactive({
      name: props.propiedad.nombre,
      direccion: props.propiedad.direccion,
      capacidad: props.propiedad.capacidad,
      dormitorios: props.propiedad.dormitorios,
      banos: props.propiedad.banos,
      precio: props.propiedad.precio,
      largo: props.propiedad.largo,
      ancho: props.propiedad.ancho,
      alto: props.propiedad.alto,
    })
  : reactive({
      name: '',
      direccion: '',
      capacidad: '',
      dormitorios: '',
      banos: '',
      precio: '',
      largo: '',
      ancho: '',
      alto: '',
    })

const services = props.propiedad
  ? reactive({
      internet: props.propiedad.servicios.internet,
      tv_cable: props.propiedad.servicios.tv_cable,
      microondas: props.propiedad.servicios.microondas,
      refrigerador: props.propiedad.servicios.refrigerador,
      lavadora: props.propiedad.servicios.lavadora,
      secadora: props.propiedad.servicios.secadora,
      servicio_de_comida: props.propiedad.servicios.servicio_de_comida,
      servicio_de_limpieza: props.propiedad.servicios.servicio_de_limpieza,
      permitir_mascotas: props.propiedad.servicios.permitir_mascotas,
    })
  : reactive({
      internet: false,
      tv_cable: false,
      microondas: false,
      refrigerador: false,
      lavadora: false,
      secadora: false,
      servicio_de_comida: false,
      servicio_de_limpieza: false,
      permitir_mascotas: false,
    })

const clickAddInventoryButton = () => {
  openInventoryPopup.value = true
}

const clickAddEmployeeButton = () => {
  openEmployeePopup.value = true
}

const addObject = object => {
  openInventoryPopup.value = false
  listInventoryObjects.value.push({ ...object })
}

const addEmployee = employee => {
  if (!employee) {
    return
  }
  openEmployeePopup.value = false
  listEmployees.value.push(employee)
}

const obtenerFechaActual = () => {
  const hoy = new Date()
  const año = hoy.getFullYear()
  const mes = String(hoy.getMonth() + 1).padStart(2, '0')
  const dia = String(hoy.getDate()).padStart(2, '0')
  return `${año}-${mes}-${dia}`
}

function createProperty() {
  const property = {
    perfil_id: store.profileId,
    nombre: form.name,
    direccion: form.direccion,
    capacidad: form.capacidad,
    dormitorios: form.dormitorios,
    banos: form.banos,
    latitud: 0,
    longitud: 0,
    servicios: {
      internet: services.internet,
      tv_cable: services.tv_cable,
      microondas: services.microondas,
      refrigerador: services.refrigerador,
      lavadora: services.lavadora,
      secadora: services.secadora,
      servicio_de_comida: services.servicio_de_comida,
      servicio_de_limpieza: services.servicio_de_limpieza,
      permitir_mascotas: services.permitir_mascotas,
    },
    precio: form.precio,
    largo: form.largo,
    ancho: form.ancho,
    alto: form.alto,
    medida: currentMeasure.value,
    disponibilidad: true,
    fecha_vencimiento: '',
    fecha_creacion: obtenerFechaActual(),
  }

  return property
}

const allowToSubmit = computed(() => {
  return (
    form.name &&
    form.direccion &&
    form.capacidad &&
    form.dormitorios &&
    form.banos &&
    form.precio
  )
})

const submitForm = () => {
  if (allowToSubmit.value) {
    propiedadesService
      .createPropiedad(createProperty())
      .then(propiedadCreada => {
        const promesas = []

        listInventoryObjects.value.forEach(object => {
          const objectToAdd = {
            nombre: object.name,
          }

          const promesaObjeto = objetoService
            .addObjeto(objectToAdd)
            .then(objetoCreado => {
              const inventario = {
                propiedad_id: propiedadCreada.id,
                objeto_id: objetoCreado.id,
                cantidad: object.cantidad,
              }

              return objetoService.addInventario(inventario)
            })

          promesas.push(promesaObjeto)
        })

        listEmployees.value.forEach(nombre => {
          const empleado = getEmployeeByName(nombre)

          const empleadoPropiedad = {
            empleado_id: empleado.id,
            propiedad_id: propiedadCreada.id,
            nombre_empleado: empleado.nombre,
          }

          const promesaEmpleado =
            empleadoService.addEmpleadoPropiedad(empleadoPropiedad)
          promesas.push(promesaEmpleado)
        })

        Promise.all(promesas)
          .then(() => {
            router.push({ name: 'Propiedades' })
          })
          .catch(error => {
            console.error('Error al procesar el formulario:', error)
          })
      })
  } else {
    console.log('Formulario no enviado')
  }
}

onMounted(() => {
  authStore.loadUserFromLocalStorage()
  getEmployees()
})
</script>

<style scoped lang="scss">
@use '../scss/_variables' as *;

.form {
  display: flex;
  flex-direction: row;
  height: 612px;
  width: fit-content;
  gap: 50px;
  margin: 100px auto;

  .add-button {
    padding-right: 17px;
  }

  .left-side {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: fit-content;
    height: fit-content;
    gap: 20px;

    .inputs {
      display: flex;
      flex-direction: column;
      max-width: 500px;
      gap: 20px;
    }

    .numeric-services {
      display: flex;
      flex-direction: row;
      gap: 50px;

      .numeric-info {
        display: flex;
        flex-direction: column;
        gap: 20px;
      }

      .services-checks {
        display: flex;
        flex-direction: column;
        gap: 20px;
        width: fit-content;
        margin-top: 50px;

        .amenities {
          display: flex;
          flex-wrap: wrap;
          justify-content: space-between;
          gap: 20px;
          width: fit-content;
        }

        .column {
          display: flex;
          justify-content: left;
          flex-direction: column;
          gap: 10px;
        }

        .services {
          display: flex;
          flex-wrap: wrap;
          justify-content: space-between;
          gap: 10px;
          width: fit-content;
        }
      }

      .divider-hor {
        width: 100%;
        height: 1px;
        background-color: $divider-color;
      }
    }
  }

  .divider-ver {
    width: 1px;
    height: 100%;
    background-color: $divider-color;
  }

  .right-side {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: fit-content;
    height: fit-content;
    gap: 20px;

    .optional-text {
      color: #9c9c9c;
    }

    .dimensions {
      .dimensions-top {
        display: flex;
        flex-direction: row;
        justify-content: space-between;

        .dimensions-title {
          display: flex;
          flex-direction: row;
          gap: 10px;
        }

        .dimensions-measures {
          display: flex;
          width: 100px;
        }
      }

      .dimensions-inputs {
        display: flex;
        flex-direction: row;
        width: fit-content;
        gap: 20px;
      }
    }

    .inventory {
      display: flex;
      flex-direction: column;
      gap: 20px;

      .inventory-top {
        display: flex;
        flex-direction: row;
        justify-content: space-between;

        .inventory-title {
          display: flex;
          flex-direction: row;
          gap: 10px;
        }

        .add-inventory-button {
          display: flex;
          justify-content: center;
        }
      }
    }

    .employee {
      display: flex;
      flex-direction: column;
      gap: 20px;

      .employee-top {
        display: flex;
        flex-direction: row;
        justify-content: space-between;

        .employee-title {
          display: flex;
          flex-direction: row;
          gap: 10px;
        }

        .add-employee-button {
          display: flex;
          justify-content: center;
        }
      }
    }

    .image {
      .image-title {
        display: flex;
        flex-direction: row;
        gap: 10px;
      }
    }

    .send-form-buttons {
      display: flex;
      flex-direction: row;
      gap: 20px;
      justify-content: right;

      .confirm-button {
        background-color: $input-border-focus-color;

        &:hover {
          filter: brightness(1.2);
        }

        &:active {
          filter: brightness(0.8);
        }
      }

      .cancel-button {
        background-color: #ca3434;

        &:hover {
          filter: brightness(1.2);
        }

        &:active {
          filter: brightness(0.8);
        }
      }
    }
  }
}
</style>
