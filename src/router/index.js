import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import GraficosView from '@/views/GraficosView.vue'
import PropiedadesView from '@/views/PropiedadesView.vue'
import VerPropiedadView from '@/views/VerPropiedadView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [{
    path: '/',
    name: 'Home',
    component: HomeView
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
  }
  ]  
})

export default router
