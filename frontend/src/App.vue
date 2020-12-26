<template lang="pug">
	v-app
		Header
		.conteudo
			Menu(v-if="verificarLogin()")
			router-view

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
		text-align: center;

	}
	.conteudo{
		display: flex;
		flex-direction: row !important;
		min-height: 92%;
		
	}

	
	
</style>
