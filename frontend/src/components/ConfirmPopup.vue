<template>
  <div v-if="visible" class="modal-overlay" @click="closePopup">
    <div class="modal-content" @click.stop>
      <h2>{{ title }}</h2>
      <slot> </slot>
      <div class="botones-container">
        <DefaultButton
          widthProp="110px"
          heightProp="40px"
          fontSizeProp="16px"
          class="confirm-button"
          @click="confirm"
          >Confirmar
        </DefaultButton>
        <DefaultButton
          widthProp="110px"
          heightProp="40px"
          fontSizeProp="16px"
          class="cancel-button"
          @click="closePopup"
          >Cancelar
        </DefaultButton>
      </div>
    </div>
  </div>
</template>

<script setup>
import DefaultButton from './DefaultButton.vue'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false,
  },
  title: {
    type: String,
    default: 'Título del Popup',
  },
})

const emit = defineEmits(['close', 'confirm'])
const confirm = () => {
  emit('close')
  emit('confirm')
}

const closePopup = () => {
  emit('close')
}
</script>

<style scoped lang="scss">
@use '../scss/_variables' as *;

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;

  .modal-content {
    display: flex;
    flex-direction: column;
    background: $bg-color;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    max-width: 500px;
    width: 100%;
    gap: 20px;

    .botones-container {
      display: flex;
      justify-content: right;
      gap: 10px;

      .confirm-button {
        background-color: #4caf50;

        &:hover {
          filter: brightness(1.2);
        }

        &:active {
          filter: brightness(0.8);
        }
      }

      .cancel-button {
        background-color: #ca3434;

        &:hover {
          filter: brightness(1.2);
        }

        &:active {
          filter: brightness(0.8);
        }
      }
    }
  }
}
</style>
