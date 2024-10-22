<template>
    <div class="button">
        <img v-if="imageName" :src="getImage(imageName)" alt="">
        <div class="hide">
            <slot></slot>
        </div>
    </div>
</template>

<script setup>
    defineProps({
        imageName : {
            type: String,
            default : ""
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

<style lang="scss">
    @use '../scss/_variables' as *;
    .button {
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
        width: 100%;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;

        &:hover {
            background-color: $button-hover-color;
        }

        &:active {
            background-color: $button-active-color;
        }
    }

    .hide {
        opacity: 1;
        visibility: visible;
        transition: opacity 0.3s linear;
    }

    @media (max-width: 800px) {
        .hide {
            opacity: 0;
            visibility: hidden;
            position: absolute;
        }
        .button {
            justify-content: center;
            padding: 5px;
            width: fit-content;
            height: fit-content;
        }
    }
</style>