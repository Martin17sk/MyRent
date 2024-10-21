import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('counter', () => {
  const userId = ref(0)
  const profileId = ref(1)

  function setUserId(id) {
    userId.value = id
  }

  function setProfileId(id) {
    profileId.value = id
  }

  return {userId, profileId, setUserId, setProfileId}
})
