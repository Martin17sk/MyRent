import { defineStore } from 'pinia'
import axios from 'axios'
import { useUserStore } from './user'
export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,
    isAuthenticated: false,
    error: null,
  }),
  actions: {
    async login(email, password) {
      try {
        const response = await axios.post(
          `http://localhost:8005/auth/login`, {
            "correo": email,
            "contrasenia":password
          }
        )
        if (response.status === 200) {
          this.user = response.data
          useUserStore().setUserId(this.user.id_usuario)
          this.isAuthenticated = true
          localStorage.setItem('user', JSON.stringify(response.data))
        }else{
          this.error = 'Hubo un problema con la autenticación'
          this.isAuthenticated = false
        }
      }catch (error) {
        this.error = 'Hubo un problema con la autenticación'
        this.isAuthenticated = false
        console.log(error)
      }
    },

    logout() {
    
      this.isAuthenticated = false
      localStorage.removeItem('user') // Limpiar la sesión
    },

    loadUserFromLocalStorage() {
      const userData = localStorage.getItem('user')
      if (userData) {
        this.user = JSON.parse(userData)
        useUserStore().setUserId(this.user.id_usuario) //Puede que esto genere problemas ojo para mañana
        this.isAuthenticated = true
      }
    },
  },
})
