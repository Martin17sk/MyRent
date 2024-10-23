import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('counter', () => {
  const userId = ref(null)
  const profileId = ref(null)

  function setUserId(id) {
    console.log('setUserId', id);
    userId.value = id
  }

  function setProfileId(id) {
    console.log('setProfileId', id);
    
    profileId.value = id
  }

  return {userId, profileId, setUserId, setProfileId}
})
