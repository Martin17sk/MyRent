<template>
    <div class="button" @click="toggleIcon"
        :style="{ width: widthProp, height: heightProp, borderRadius: borderRadiusProp }">
        <img :src="currentIcon" alt="Icono" />
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const props = defineProps({
    widthProp: {
        type: String,
        default: '50px'
    },
    heightProp: {
        type: String,
        default: '50px'
    },
    borderRadiusProp: {
        type: String,
        default: '50px'
    },
    initialImage: {
        type: String,
        required: true
    },
    otherImage: {
        type: String,
        required: true
    }
});

const isInitialImage = ref(true);

const currentIcon = computed(() => {
    const imageName = isInitialImage.value ? props.initialImage : props.otherImage;
    try {
        return new URL(`../assets/images/${imageName}`, import.meta.url).href;
    } catch (error) {
        console.error(`Image ${imageName} not found`);
        return "";
    }
});

const toggleIcon = () => {
    isInitialImage.value = !isInitialImage.value;
};
</script>

<style scoped lang="scss">
@use '../scss/_variables' as *;

.button {
    border: none;
    background: $button-color;
    cursor: pointer;
    padding: 0;

    img {
        width: 40px;
        height: 40px;
        margin: auto;
    }

    &:hover {
        background-color: $button-hover-color;
    }

    &:active {
        background-color: $button-active-color;
    }
}
</style>