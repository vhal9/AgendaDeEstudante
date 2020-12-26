<template lang="pug">
	#menu-lateral
		v-card.mx-auto(heigth='100%', outlined)
		v-navigation-drawer(expand-on-hover, dark)
			v-list
				v-list-item
					v-list-item-icon
						v-icon mdi-account
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
					{ title: "Nova Tarefa", icon: "mdi-calendar-plus", link:"/cadastroTarefa"},
					{ title: "Nova Etiqueta", icon: "mdi-tag", link:"/cadastroEtiqueta"},
				],
			};
		},
		methods: {
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
		max-width: 30%;
		min-width: 10%;
	}
	
	.mx-auto{
		position: fixed;
	}
	
	.v-navigation-drawer {
		transition-duration: 0.6s !important;
	}
	
	.v-navigation-drawer.v-navigation-drawer--is-mouseover-navigation-drawer--open.v-navigation-drawer--open-on-hover.theme--light{
		width: 300px !important;
	}
	
	.v-list-item--dense, .v-list--dense .v-list-item {
		min-height: 55px !important;
	}

</style>
