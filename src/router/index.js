import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import GraficosView from '@/views/GraficosView.vue'

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
  }
  ]  
})

export default router
