import LoginView from '@/views/LoginView.vue'
import RecoverPasswordView from '@/views/RecoverPasswordView.vue'
import RegisterView from '@/views/RegisterView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/login', name: 'Login', component: LoginView },
    { path: '/register', name: 'Register', component: RegisterView },
    {
      path: '/recover-password',
      name: 'Recover',
      component: RecoverPasswordView,
    },
  ],
})

export default router
