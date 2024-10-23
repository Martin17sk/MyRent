<template>
    <div class="image-label-button">
        <img :src="getImage(imageName)" :style="{width: widthImageProp, height:heightImageProp}"/>
        <span>{{label}}</span>
    </div>
</template>

<script setup>
defineProps({
    imageName: {
        type: String,
        default: ''
    },
    label: {
        type: String,
        default: ''
    },
    widthImageProp: {
        type: String,
        default: '20px'
    },
    heightImageProp: {
        type: String,
        default: '20px'
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

<style scoped lang="scss">
@use '../scss/_variables' as *;

.image-label-button {
    display: flex;
    gap: 7px;
    align-items: center;
    border-radius: 5px;
    padding: 5px 10px;
    cursor: pointer;
    user-select: none;
    font-family: $font;
    font-size: 14px;
    font-weight: 500;
    background-color: $button-color;

    &:hover {
        background-color: $button-hover-color;
    }

    &:active {
        background-color: $button-active-color;
    }
}
</style>