<template>
    <div class="text-input">
        <div class="label">
            <label :for="inputId" >{{ label }}</label>
            <label :for="inputId" class="red" v-if="isObligatory"> * </label>
        </div>
        <input :id="inputId" type="text" :placeholder="placeholder" :width="widthProp" :height="heightProp" @input="onInput" />
    </div>
</template>

<script setup>
import { computed, toRefs } from 'vue';

const text = defineModel();
const props = defineProps({
    label: {
        type: String,
        default: ''
    },
    placeholder: {
        type: String,
        default: ''
    },
    widthProp: {
        type: String,
        default: '500px'
    },
    heightProp: {
        type: String,
        default: '50px'
    },
    isObligatory: {
        type: Boolean,
        default: false
    }
});

const emit = defineEmits(['update:modelValue']);

const { modelValue } = toRefs(props);

const inputId = computed(() => props.label.replace(/\s+/g, '-').toLowerCase());

const onInput = (event) => {
    emit('update:modelValue', event.target.value);
};
</script>

<style scoped lang="scss">
@use '../scss/_variables' as *;

.text-input {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;

    .label {
        display: flex;
        gap: 0.5rem;
        .red {
            color: red;
        }
    }

    input {
        color: white;
        background-color: $input-color;
        border-color: $input-border-color;
        padding: 0.5rem;
        box-sizing: border-box;
        border-radius: 5px;
        border: 1px solid #66666659;
        caret-color: white;

        &:focus {
            border-color: $input-border-focus-color;
            outline: none;
        }

        &::placeholder {
            color: #ffffff8e;
        }
    }

    label {
        display: flex;
    }
}
</style>