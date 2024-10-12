<template>
    <div class="main-layout">
        <DefaultHeader @openSideBar="isSideBarOpen=true" />
        <SideBar :isOpen=isSideBarOpen @close="isSideBarOpen=false"/>
    </div>
    <div class="content">
        <slot></slot>
    </div>
</template>

<script setup lang="ts">
    import DefaultHeader from '@/components/DefaultHeader.vue';
    import SideBar from '@/components/SideBar.vue';
    import { ref } from 'vue';
    const isSideBarOpen = ref(false);
</script>

<style lang="scss">

</style>