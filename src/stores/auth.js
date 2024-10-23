import { defineStore } from 'pinia';
import axios from 'axios';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,    // Aquí almacenaremos los datos del usuario autenticado
    isAuthenticated: false,
    error: null,
  }),
  actions: {
    async login(email, password) {
      try {
        const response = await axios.get(`http://localhost:3000/users?correo=${email}&contraseña=${password}`);

        const user = response.data[0];  // Encontrar al usuario con las credenciales
        console.log(response.data)

        if (user) {
          console.log(user)
          this.user = user;
          this.isAuthenticated = true;
          // localStorage.setItem('user', JSON.stringify(user));
        } else {
          this.error = 'Usuario o contraseña incorrectos';
          this.isAuthenticated = false;
        }
      } catch (error) {
        this.error = 'Hubo un problema con la autenticación';
        this.isAuthenticated = false;
        console.log(error);
      }
    },

    logout() {
      this.user = null;
      this.isAuthenticated = false;
      localStorage.removeItem('user');  // Limpiar la sesión
    },

    loadUserFromLocalStorage() {
      const userData = localStorage.getItem('user');
      if (userData) {
        this.user = JSON.parse(userData);
        this.isAuthenticated = true;
      }
    },
  },
});