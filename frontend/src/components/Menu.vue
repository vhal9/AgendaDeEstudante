<template lang="pug">
	#menu-lateral
		v-navigation-drawer(expand-on-hover dark)
			v-list
				v-list-item
					v-list-item-icon
						v-icon mdi-account-circle
					v-list-item-content
						v-list-item-title Victor Hugo
				v-divider
				v-list-item-group
					v-list-item(v-for='item in items' :key='item.title' :to='item.link')
						v-list-item-icon
							v-icon {{item.icon}}
						v-list-item-content
							v-list-item-title {{item.title}}


</template>

<script>
	import UsuarioService from "../services/usuarioService";
	export default {
		name: "Menu",
		data() {
			return {
				nomeUsuario: "",
				items: [
					{ title: "Home", icon: "mdi-home", link:"/"},
					{ title: "Nova Tarefa", icon: "mdi-calendar-plus", link:"/cadastrarTarefa"},
					{ title: "Nova Etiqueta", icon: "mdi-tag", link:"/cadastrarEtiqueta"},
				],
			};
		},
		methods: {
			logout() {
				console.log("testando");
				localStorage.removeItem("nome");
				console.log("removido");
				this.$router.push("/login");
			},
			getNomeUsuario() {
				UsuarioService.getNomeUsuario().then((resposta) => {
					this.nomeUsuario = resposta.data;
				});
			},
		},
		mounted() {
			this.getNomeUsuario();
		}, 
	};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#menu-lateral{
		min-height: 100%;
		height: 100%;
	}
	.theme--light.v-application{
		background-color: blueviolet;
	}

</style>
