<script setup>
import { ref } from 'vue';

const props = defineProps({
    widthProp: { Required: true, type: String },
    labelText: {
        Required: true,
        type: String
    },
    placeholder: {
        Required: true,
        type: String
    },
    type: {
        Required: true,
        type: String
    },
})
const widthPropVal = ref(props.widthProp);

</script>
<template>
    <div class="input">
        <label for="thisInput">{{ labelText }}</label>
        <input :style="{ width: widthPropVal }" class="input-text" :type="type" id="thisInput" name="thisInput" :placeholder="placeholder">
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