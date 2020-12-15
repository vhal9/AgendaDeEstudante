<template>
    <v-app>
      <Menu />
      <router-view /> 
    </v-app>
    
</template>

<script>
import Menu from './components/Menu.vue'
import 'vue-toast-notification/dist/theme-sugar.css';
export default {
  name: 'App',
  components: {
    Menu,
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
</style>
