<template>
    <div class="button">
        <img v-if="image" :src="getImage(image)" alt="">
    </div>
</template>

<script setup>
    defineProps({
        image : {
            type: String,
            default: ''
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
        width: 50px;
        height: 50px;
        color: $button-text-color;
        padding: 0;
        border-radius: 5px;
        align-items: center;
        justify-content: center;
        cursor: pointer;

        &:hover {
            background-color: $button-hover-color;
        }

        &:active {
            background-color: $button-active-color;
        }

        img {
            width: 40px;
            height: 40px;
        }
    }
</style>