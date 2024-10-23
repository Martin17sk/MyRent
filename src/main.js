import './assets/main.css'
import VCalendar from 'v-calendar'
import 'v-calendar/style.css'

// Use plugin with optional defaults

// Use plugin with optional defaults

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(VCalendar, {})

app.use(createPinia())
app.use(router)

app.mount('#app')
