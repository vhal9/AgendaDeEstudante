<template lang="pug">
	v-app.container
		Menu(v-if="verificarLogin()")
		Header
		router-view.col-sm-6

</template>

<script>
	import Menu from './components/Menu.vue'
	import Header from './components/Header.vue'
	import 'vue-toast-notification/dist/theme-sugar.css';
	export default {
		name: 'App',
		components: {
			Menu,
			Header
		},
		methods:{
			verificarLogin(){
				console.log("teste");
				if(localStorage.nome === undefined || localStorage.nome === null) return false;
				return true
			}
		}
	};
</script>

<style>
	#app {
		font-family: Avenir, Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		display: flex;
		flex-direction: column;
		justify-content: center;
		color:#3949AB;
	}

	
	
</style>
