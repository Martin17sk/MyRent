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
        const response = await axios.post(`http://localhost:8005/auth/login`, {
          correo: email,
          contrasenia: password,
        })
        if (response.status === 200) {
          this.user =(response.data)
          localStorage.setItem('user', JSON.stringify(response.data))
          useUserStore().setUserId(this.user.id_usuario)
          this.isAuthenticated = true
          console.log(this.user)
        } else {
          this.error = 'Hubo un problema con la autenticación'
          this.isAuthenticated = false
        }
      } catch (error) {
        this.error = 'Hubo un error con la autenticación'
        console.log(this.error + ' ')
        this.isAuthenticated = false
        console.log(error)
      }
    },

    logout() {
      this.isAuthenticated = false
      localStorage.removeItem('user') // Limpiar la sesión
    },

    loadUserFromLocalStorage() {
      const user = localStorage.getItem('user') // Obtener la sesión
      if (user) {
        this.user = JSON.parse(user)
        useUserStore().setUserId(this.user.id_usuario)
        this.isAuthenticated = true
      }
    },
    async validateSession() {
      const reponse = await axios.get('http://localhost:8005/auth/validate', {
        headers: { "Authorization" : `Bearer ${this.user.token}`, "correo" : this.user.correo },
      })
      if (reponse.status === 200) {
        console.log('Sesión válida')
        this.isAuthenticated = true
      }else{
        console.log('Sesión no válida')
        this.isAuthenticated = false
      }
    },
  },
})
