
import router from './router/router';
import "./assets/activestyle.css";
//import "bootstrap/dist/css/bootstrap.min.css";

import { createApp } from 'vue'
import App from './App.vue'

const app = createApp(App);
app.use(router);
app.mount('#app');