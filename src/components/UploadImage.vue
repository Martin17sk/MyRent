<template>
    <div class="button" :style="{width:widthProp, height:heightProp}">
        <slot></slot>
        <h2>{{ label }}</h2>
    </div>
</template>

<script setup>
defineProps({
    label: {
        type: String,
        default: ''
    },
    widthProp: {
        type: String,
        default: '200px'
    },
    heightProp: {
        type: String,
        default: '200px'
    }
});
</script>

<style scoped lang="scss">
@use '../scss/_variables' as *;

.button{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 10px;
    border-radius: 10px;
    padding: 10px;
    cursor: pointer;
    user-select: none;
    font-family: $font;
    font-size: 14px;
    font-weight: 500;
    background-color: $button-color;

    h2{
        opacity: 0.5;
    }

    &:hover {
        background-color: $button-hover-color;
    }
    &:active {
        background-color: $button-active-color;
    }
}
</style>