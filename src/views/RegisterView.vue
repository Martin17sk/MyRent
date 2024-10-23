<script setup>
import { ref, computed } from 'vue';
import UsuarioService from '@/services/UsuarioService';

const usuarioService = new UsuarioService();
const email = ref("");
const confirmEmail = ref("");
const password = ref("");
const confirmPassword = ref("");
const passwordVisible = ref(false);
const togglePasswordVisibility = () => {
    passwordVisible.value = !passwordVisible.value;
};
const passwordConfVisible = ref(false);
const togglePasswordConfVisibility = () => {
    passwordConfVisible.value = !passwordConfVisible.value;
};
const passwordFieldType = computed(() => (passwordVisible.value ? 'text' : 'password'));
const passwordConfFieldType = computed(() => (passwordConfVisible.value ? 'text' : 'password'));

const obtenerFechaActual = () => {
    const hoy = new Date();
    const año = hoy.getFullYear();
    const mes = String(hoy.getMonth() + 1).padStart(2, '0');
    const dia = String(hoy.getDate()).padStart(2, '0');
    return `${año}-${mes}-${dia}`;
};


const submit = async () => {
    
    if ((password.value !== confirmPassword.value || password.value === '')) {
        alert('Las contraseñas no coinciden');
        return;
    }
    if ((email.value !== confirmEmail.value || email.value === '')) {
        alert('Los correos electrónicos no coinciden');
        return;
    }

    usuarioService.getUsuarioByEmail(email.value).then((response) => {
        console.log(response);
        if (response.length > 0) {
            alert('El correo electrónico ya está registrado');
            return;
        }
    });

    const user = {
        correo: email.value,
        contraseña: password.value,
        fecha_registro: obtenerFechaActual(),
        ultimo_acceso: obtenerFechaActual(),
    };



    usuarioService.addUsuario(user).then((response) => {
        console.log(response);
        
        if (response === 200) {
            alert('Usuario registrado correctamente');
        }
    });
};

</script>

<template>
    <div class="content">
        <form class="form" @submit.prevent="submit">
            <div class="header-row">
                <h1 class="center-text">Registro</h1>

                <RouterLink class="clean" to="Login">
                    <h1 class="clickable" style="color: rgb(20, 174, 92);">X</h1>
                </RouterLink>

            </div>
            <div class="form_info">
                <div class="nombre">
                    <label for="nombre">Correo Electrónico</label>
                    <input class="input-text" type="text" id="nombre" name="nombre"
                        placeholder="Ingresa tu correo electrónico" v-model="email">
                </div>
                <div class="email">
                    <label for="email">Confirmar Correo Electrónico</label>
                    <input class="input-text" type="email" id="email" name="email"
                        placeholder="Confirma tu correo electrónico" v-model="confirmEmail">
                </div>
                <div class="contraseña">
                    <div class="contraseña-label">
                        <label for="contraseña">Contraseña</label>
                        <div @click="togglePasswordVisibility" class="hide">
                            <img class="emphasis"
                                :src="passwordVisible ? 'src/assets/images/hide-icono.svg' : 'src/assets/images/show-icono.svg'"
                                alt="hide">
                        </div>
                    </div>
                    <input class="input-text" :type="passwordFieldType" v-model="password" id="contraseña"
                        name="contraseña" placeholder="Ingresa tu contraseña">
                </div>
                <div class="confirm-contraseña">
                    <div class="contraseña-label">
                        <label for="confirm-contraseña">Confirmar Contraseña</label>
                        <div @click="togglePasswordConfVisibility" class="hide">
                            <img class="emphasis"
                                :src="passwordConfVisible ? 'src/assets/images/hide-icono.svg' : 'src/assets/images/show-icono.svg'"
                                alt="hide">
                        </div>
                    </div>
                    <input class="input-text" :type="passwordConfFieldType" v-model="confirmPassword"
                        id="confirm-contraseña" name="confirm-contraseña" placeholder="Confirma tu contraseña">
                </div>
            </div>
            <div class="register-button">
                <button type="submit">Registrarse</button>
            </div>
        </form>
    </div>
</template>

<style scoped>


.clickable {
    cursor: pointer;
    transition: all .2s ease-in-out;
}

.clickable:hover {
    filter: drop-shadow(0px 0px 5px rgba(20, 174, 92, 0.5));
}

.header-row {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}

.content {
    display: flex;
    flex-direction: column;
    gap: 24px;
    width: fit-content;
    height: fit-content;
    margin: 22px auto;
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

.nombre,
.email,
.contraseña,
.confirm-contraseña {
    display: flex;
    flex-direction: column;
    gap: 10px;
}

.contraseña-label,
.confirm-contraseña-label {
    display: flex;
    justify-content: space-between;
}

.hide {
    display: flex;
    flex-direction: row;
    color: #666666;
    gap: 6px;
    cursor: pointer;
}

.hide img {
    user-drag: none;
}

.register-button {
    width: fit-content;
    margin: auto;
}

.register-button button {
    width: 32vw;
    padding: 12px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.register-button button:hover {
    background-color: #218838;
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
</style>