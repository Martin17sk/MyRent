<template>
    <div class="checkbox-container">
        <input type="checkbox" id="default-checkbox">
        
        <label for="default-checkbox">
            <img src="../assets/images/checkbox-icono.svg">
            {{ labelText }}
        </label>
    </div>
</template>

<script setup>
defineProps({
    checked: {
        type: Boolean,
        default: false
    },
    labelText: {
        type: String,
        default: ''
    }
});
</script>

<style scoped lang="scss">
@use '../scss/_variables' as *;
.checkbox-container {
    position: relative;
    display: flex;
    flex-direction: row;
    align-items: center;
    gap: 10px;
    width: fit-content;
    height: fit-content;

    #default-checkbox{
        z-index: 1;
        appearance: none;
        cursor: pointer;
        width: 18px;
        height: 18px;
        background-color: transparent;
        border: 1px solid #000000;
        border-radius: 2px;
        transition: border-color 0.1s linear;

        
    }

    #default-checkbox + label img {
        position: absolute;
        top: 3px;
        left: 0;
        width: 18px;
        height: 18px;
        opacity: 0;
        transition: opacity 0.1s linear;
    }

    #default-checkbox:checked {
        background-repeat: no-repeat;
        background-position-x: center;
        background-position-y: center;
        background-size: 18px 18px;
        border-color: $input-border-focus-color;
    }

    #default-checkbox:checked + label img {
        opacity: 1;
    }
    
}


</style>