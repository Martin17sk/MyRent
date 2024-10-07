import { defineStore } from "pinia";

export const useAuthStore = defineStore('auth', {
    state : () => {
        return {
            token : null,
            user : null
        }
    },
    getters: {
        // Define the getters of the store
    },
    actions : {
        // Define the actions of the store
    }
})