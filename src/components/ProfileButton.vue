<template>
    <div class="button" :style="{ width: widthProp, height: heightProp, border: borderProp }">
        <img :src="getImage(image)" :style="{ width: widthProp * 0.6, height: heightProp * 0.6 }">
    </div>
</template>

<script setup>
defineProps({
    widthProp: {
        type: String,
        default: '50px'
    },
    heightProp: {
        type: String,
        default: '50px'
    },
    image: {
        type: String,
        default: ''
    },
    borderProp: {
        type: String,
        default: 'none'
    }
});

const getImage = (imageName) => {
    try {
        return new URL(`../assets/images/${imageName}`, import.meta.url).href;
    } catch (error) {
        console.error(`Image ${imageName} not found`);
        return "";
    }
};
</script>

<style lang="scss" scoped>
@use '../scss/_variables' as *;

.button {
    display: flex;
    border-radius: 30px;
    background-color: $button-color;
    align-items: center;
    justify-content: center;

    &:hover {
        background-color: $button-hover-color;
    }

    &:active {
        background-color: $button-active-color;
    }
}
</style>