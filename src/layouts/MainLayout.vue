<template>
    <div class="main-layout">
        <DefaultHeader @openSideBar="isSideBarOpen=true" @openPerfiles="isProfilePickerOpen=true"/>
        <SideBar :isOpen=isSideBarOpen @close="isSideBarOpen=false"/>
        <ProfilePicker :visible="isProfilePickerOpen" @close="isProfilePickerOpen=false"/>
    </div>
    <slot></slot>
</template>

<script setup>
    import DefaultHeader from '@/components/DefaultHeader.vue';
    import SideBar from '@/components/SideBar.vue';
    import ProfilePicker from '@/components/ProfilePicker.vue';
    import { ref } from 'vue';
    const isSideBarOpen = ref(false);
    const isProfilePickerOpen = ref(false);
</script>

<style lang="scss">

</style>