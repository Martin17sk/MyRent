import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import ConfigView from '@/views/ConfigView.vue'
import GraficosView from '@/views/GraficosView.vue'
import PropiedadesView from '@/views/PropiedadesView.vue'
import VerPropiedadView from '@/views/VerPropiedadView.vue'
import AgregarPropiedadView from '@/views/AgregarPropiedadView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import RecoverPasswordView from '@/views/RecoverPasswordView.vue'
import MapaView from '@/views/MapaView.vue'
import PerfilesView from '@/views/PerfilesView.vue'
import CompararView from '@/views/CompararView.vue'
import ConfigCuentaView from '@/views/ConfigCuentaView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
routes: [{
    path: '/',
    name: 'Home',
    redirect: {name: 'Login'}
  },
  {
    path: '/graficos',
    name: 'Graficos',
    component: GraficosView
  },
  {
    path: '/propiedades',
    name: 'Propiedades',
    component: PropiedadesView
  },
  {
    path: '/propiedades/:id',
    name: 'Propiedad',
    component: VerPropiedadView
  },
  {
    path: '/agregar-propiedad',
    name: 'AgregarPropiedad',
    component: AgregarPropiedadView
  },
  {
    path: '/mapa',
    name: 'Mapa',
    component: MapaView
  },
  { path: '/login',
    name: 'Login',
    component: LoginView
  },
  { path: '/register',
    name: 'Register',
    component: RegisterView
  },
  {
    path: '/recover-password',
    name: 'Recover',
    component: RecoverPasswordView,
  },
  {
    path: '/perfiles',
    name: 'Perfiles',
    component: PerfilesView,
  },
  {
    path: '/comparar',
    name: 'Comparar',
    component: CompararView
  },
  {
    path: '/config',
    name: 'Config',
    component: ConfigView
  },
  {
    path: '/config-cuenta',
    name: 'ConfigCuenta',
    component: ConfigCuentaView
  }
  ]  
})

export default router
