<script setup>
import InputComponent from '@/components/InputComponent.vue';
import { computed, ref } from 'vue';

const password = ref('')
const passwordVisible = ref(false);
const togglePasswordVisibility = () => {
    console.log("tocado")
    passwordVisible.value = !passwordVisible.value;
};
const passwordFieldType = computed(() => (passwordVisible.value ? 'text' : 'password'));
</script>
<template>
    <div class="page">
        <div class="content">
            <div class="form">
                <h1 class="center-text">Iniciar sesión</h1>
                <div class="form_info">
                    <div class="email">
                        <label for="email">Correo Electrónico o nombre de usuario</label>
                        <input class="input-text" type="email" id="email" name="email"
                            placeholder="Ingresa tu correo electrónico o nombre de usuario">
                    </div>
                    <div class="contraseña">
                        <div class="contraseña-label">
                            <label for="contraseña">Contraseña</label>
                            <div @click="togglePasswordVisibility" class="hide">
                                <img class="emphasis"
                                    :src="passwordVisible ? 'src/assets/images/hide-icono.svg' : 'src/assets/images/show-icono.svg'"
                                    alt="hide">
                                <!-- {{ passwordVisible ? 'Ocultar' : 'Mostrar' }} -->
                            </div>
                        </div>

                        <input class="input-text" :type="passwordFieldType" v-model="password" id="contraseña"
                            name="contraseña" placeholder="Ingresa tu contraseña">
                        <div class="checkbox">
                            <input type="checkbox" name="" id="checkbox">
                            <label id="checkbox-label" for="checkbox">Recordar contraseña</label>
                        </div>
                    </div>
                    <div class="iniciar-sesion">
                        <button class="green-focus" type="button">Iniciar sesión</button>
                    </div>
                </div>
            </div>
            <div class="options">
                <h3 class="underline">¿Olvidaste tu contraseña?</h3>
                <span class="text__row">
                    <h4>¿No tienes una cuenta?
                        <RouterLink class="link" to="Register">Registrarse</RouterLink>
                    </h4>
                </span>
                <div class="continue-with">
                    <div class="divider">
                        <hr>
                        <h2>O continuar con</h2>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>
<style lang="scss" scoped>
.link {
    text-decoration: underline;
    color: inherit;
    transition: all .2s ease-in-out;

    &:hover {
        text-shadow: 0px 0px 10px rgba(20, 174, 92, 0.9);
    }
}

.emphasis {
    transition: all .2s ease-out;
}

.emphasis:hover {
    filter: drop-shadow(0px 0px 10px rgba(20, 174, 92, .8))
}

.center-text {
    text-align: center;
}

.underline {
    text-decoration: underline;
}

button {
    appearance: none;
    padding: 1.69vh 1.25vw;
    font-family: var(--font);
    font-size: 1.375rem;
    font-weight: 50;
    color: #ffffffe0;
    background-color: #303036;
    border-radius: 32px;
    border: none;
    cursor: pointer;
}

button:hover {
    background-color: #3f3f44;
}

.input-text {
    padding: 20px 24px;
    color: #ffffff;
    background-color: #303036;
    border-radius: 12px;
    border: 1px solid #66666659;
}

.input-text:focus {
    border: 1px solid rgb(20, 174, 92);
    outline: none;
}

:root {
    --font: 'Poppins', sans-serif;
}

body {
    overflow-x: hidden;
}

h2 {
    font-size: 1rem;
}

h4 {
    margin: 0;
}

.header-nav__back {
    margin-top: 0;
    margin-left: 0;
    width: fit-content;
    height: fit-content;
    display: flex;
    padding: 4px;
}

.header-nav__back img {
    width: 24px;
    height: 24px;
    margin: 24px;
}


.content {
    display: flex;
    flex-direction: column;
    gap: 24px;
    width: fit-content;
    height: fit-content;
    margin: 22px auto 0 auto;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
}

.form {
    display: flex;
    flex-direction: column;
    width: fit-content;
    height: fit-content;
    gap: 32px;
}

.form_info {
    display: flex;
    flex-direction: column;
    gap: 24px;
}

.email {
    display: flex;
    flex-direction: column;
    gap: 10px;
}

.contraseña {
    display: flex;
    flex-direction: column;
    gap: 10px;
}

.contraseña-label {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.hide {
    display: flex;
    flex-direction: row;
    color: #666666;
    gap: 6px;
    user-select: none;

    img {
        user-drag: none;
        -webkit-user-drag: none;
        user-select: none;
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
}

.hide:hover {
    cursor: pointer;
}

.checkbox {
    display: flex;
    flex-direction: row;
    gap: 10px;
}

.iniciar-sesion {
    width: fit-content;
    margin: auto;
}

.iniciar-sesion button {
    width: 32vw;
}

.green-focus:focus {
    border: 1px solid rgb(20, 174, 92);
    outline: none;
}


.options {
    display: flex;
    flex-direction: column;
    gap: 24px;
    align-items: center;
}

.text__row h4 {
    color: white;
}

.continue-with {
    display: flex;
    flex-direction: column;
    gap: 12px;
}

.divider {
    width: fit-content;
    display: flex;
    flex-direction: row;
    gap: 24px;
    align-items: center;
}

.divider hr {
    width: 186px;
    color: #66666642;
}
</style>