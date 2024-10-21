<script setup>
import { ref, watch } from 'vue';

const props = defineProps({
    modelValue: {
        required: true,
        type: [String, Number]  // Dependerá de tu tipo de dato
    },
    widthProp: {
        required: true,
        type: String
    },
    labelText: {
        required: true,
        type: String
    },
    placeholder: {
        required: true,
        type: String
    },
    type: {
        required: true,
        type: String
    }
});

const emit = defineEmits(['update:modelValue']);
const inputText = ref(props.modelValue);  // Define el valor del input inicial

const updateModel = () => {
    emit('update:modelValue', inputText.value);  // Emitir evento para actualizar el modelo padre
};

// Actualizar el inputText cuando el valor de props.modelValue cambie
watch(() => props.modelValue, (newValue) => {
    inputText.value = newValue;
});

const widthPropVal = ref(props.widthProp);

</script>

<template>
    <div class="input">
        <label for="thisInput">{{ labelText }}</label>
        <input v-model="inputText" @input="updateModel" :style="{ width: widthPropVal }" class="input-text" :type="type"
            id="thisInput" name="thisInput" :placeholder="placeholder" />
    </div>
</template>
<style lang="scss" scoped>
.input {
    display: flex;
    flex-direction: column;
    gap: 10px
}

.input-text {
    padding: 20px 24px;
    color: #ffffff;
    background-color: #303036;
    border-radius: 12px;
    border: 1px solid #66666659;
}

.input-text:focus {
    border: 1px solid rgb(20, 174, 92);
    outline: none;
}
</style>