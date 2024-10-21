<script setup>
import { ref } from 'vue';

const email = ref('');
const successMessage = ref('');
const errorMessage = ref('');

const sendRecoveryLink = () => {
    if (validateEmail(email.value)) {
        // Simular el envío del correo
        successMessage.value = 'El enlace de recuperación ha sido enviado a tu correo electrónico.';
        errorMessage.value = '';
    } else {
        errorMessage.value = 'Por favor, ingresa un correo electrónico válido.';
        successMessage.value = '';
    }
};

// Validar formato de correo electrónico
const validateEmail = (email) => {
    const re = /^(([^<>()\\[\]\\.,;:\s@"]+(\.[^<>()\\[\]\\.,;:\s@"]+)*)|(".+"))@(([^<>()[\]\\.,;:\s@"]+\.)+[^<>()[\]\\.,;:\s@"]{2,})$/i;
    return re.test(String(email).toLowerCase());
};
</script>

<template>
    <div class="page">
        <div class="content">
            <h1>Recuperación de Contraseña</h1>
            <p>Ingresa tu correo electrónico para recibir un enlace de recuperación.</p>

            <div class="email-input">
                <label for="email">Correo Electrónico</label>
                <input class="input-text" v-model="email" type="email" id="email"
                    placeholder="Ingresa tu correo electrónico" />
            </div>

            <button @click="sendRecoveryLink" class="recovery-button">Enviar enlace de recuperación</button>

            <p v-if="successMessage" class="success-message">{{ successMessage }}</p>
            <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
        </div>
    </div>
</template>

<style scoped>
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

.page {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #181818;
}

.content {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
    width: 100%;
    max-width: 400px;
    padding: 20px;
    background-color: #181818;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.email-input {
    display: flex;
    flex-direction: column;
    gap: 10px;
    width: 100%;
}

.email-input input {
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    width: 100%;
}

.recovery-button {
    padding: 12px 20px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: 100%;
}

.recovery-button:hover {
    background-color: #28a745;
}

.success-message {
    color: green;
    margin-top: 10px;
}

.error-message {
    color: red;
    margin-top: 10px;
}
</style>