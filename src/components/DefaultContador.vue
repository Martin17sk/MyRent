<template>
    <div class="container" :style="{ backgroundColor: contadorColor, width: widthProp, height: heightProp }">
        <h2 id="value">{{ value }}</h2>
        <div class="divider" :style="{ backgroundColor: dividerColor }"></div>
        <h2 id="max-value">{{ maxValue }}</h2>
    </div>
</template>

<script setup>
import { ref } from 'vue';

defineProps({
    value: {
        type: Number,
        default: 0
    },
    maxValue: {
        type: Number,
        default: 10
    },
    dividerColor: {
        type: String,
        default: 'black'
    },
    contadorColor: {
        type: String,
        default: 'white'
    },
    widthProp: {
        type: String,
        default: '100px'
    },
    heightProp: {
        type: String,
        default: '50px'
    }
});
</script>

<style scoped lang="scss">
.container {
    display: flex;
    position: relative;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    gap: 10px;
    border-radius: 10px;
    user-select: none;

    h2 {
        position: absolute;
    }

    #value {
        margin: auto 50% auto auto;
    }

    .divider {
        position: absolute;
        width: 1px;
        height: 70%;
    }

    #max-value {
        margin: auto auto auto 50%;
    }
}
</style>