import Vue from 'vue'
import App from './App.vue'
import router from './router'

import vuetify from './plugins/vuetify';

import VueToast from 'vue-toast-notification';

import { BootstrapVue,  BootstrapVueIcons} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Install BootstrapVue
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

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
