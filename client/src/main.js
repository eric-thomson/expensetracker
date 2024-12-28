import { createApp } from 'vue';
import App from './App.vue';
import router from './router'
import 'vuetify/styles'; // Ensure you import Vuetify styles
import '@mdi/font/css/materialdesignicons.css'; // Optional: Material Design Icons
import 'roboto-fontface/css/roboto/roboto-fontface.css'; // Optional: Roboto Font
import vuetify from './plugins/vuetify'; // Assuming you use a separate plugins file

const app = createApp(App);

app.use(router);
app.use(vuetify); // Register Vuetify as a plugin
app.mount('#app');
