<template>
    <div class="switch">
      <input type="checkbox" id="toggle" class="switch-input" v-model="isChecked" @click="toggleSwitch">
      <label for="toggle" class="switch-label">
        <span class="switch-inner"></span>
        <span class="switch-button"></span>
      </label>
    </div>
</template>
  
<script setup>
    const isChecked = defineModel(false);
    const toggleSwitch = () => {
      isChecked.value = !isChecked.value;
    };
</script>
  
<style lang="scss"scoped>
    @use "../scss/_variables" as *;

    .switch {
        display: inline-block;
        position: relative;
    }
    
    .switch-input {
        display: none;
    }
    
    .switch-label {
        display: block;
        width: 40px;
        height: 20px;
        background-color: #ccc;
        border-radius: 30px;
        position: relative;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    
    .switch-inner {
        display: block;
        width: 100%;
        height: 100%;
        border-radius: 30px;
        background-color: #e0e0e0;
        transition: background-color 0.3s ease;
    }
    
    .switch-button {
        display: block;
        width: 43%;
        height: 86%;
        background-color: #fff;
        border-radius: 50%;
        position: absolute;
        top: 1px;
        left: 2px;
        transition: left 0.3s ease;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    }
    
    .switch-input:checked + .switch-label .switch-inner {
        background-color: $input-border-focus-color;
    }
    
    .switch-input:checked + .switch-label .switch-button {
        left: 21px;
    }
</style>
  