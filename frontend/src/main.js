import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import VueToast from 'vue-toast-notification';

import { ValidationProvider } from 'vee-validate';

Vue.component('ValidationProvider', ValidationProvider);

Vue.use(VueToast, {
  duration: 2000,
  position: 'top-right',
})


Vue.config.productionTip = false



Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App),
}).$mount('#app')
