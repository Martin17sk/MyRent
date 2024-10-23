import './assets/main.css'
import { setupCalendar, Calendar, DatePicker } from 'v-calendar';
import 'v-calendar/style.css';


// Use plugin with optional defaults

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.component('VCalendar', Calendar)
app.component('VDatePicker', DatePicker)
app.use(router)

app.mount('#app')
