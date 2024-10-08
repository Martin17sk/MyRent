<template>
    <div class="button">
        <img v-if="imageName" :src="getImage(imageName)" alt="">
        <slot></slot>
    </div>
</template>

<script setup lang="ts">
    import { defineProps} from 'vue';

    defineProps({
        imageName : {
            type: String,
            default : ""
        } 
    });

    const getImage = (imageName : string) => {
        return require(`../assets/images/${imageName}`);
    }

</script>

<style lang="scss">
    .button {
        display: flex;
        gap: 7px;
        align-items: center;
        border-radius: 5px;
        padding: 5px 10px;
        cursor: pointer;

        &:hover {
            background-color: $button-hover-color;
        }

        &:active {
            background-color: $button-active-color;
        }
    }
</style>