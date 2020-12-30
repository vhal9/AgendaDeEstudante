<template lang="pug">
	.login.container
		.form
			h2.titulo Bem vindo
			v-form(ref='form', lazy-validation)
				v-text-field(v-model='user.login' label='Username' :rules='loginRule')
				v-text-field(v-model='user.senha' label='Senha' type='password' :rules='senhaRule')
				Botao(label="Entrar", @clickBotao='logar', :disabled="isDisabled")
				Botao(label="Cadastrar" @clickBotao='cadastrarUsuario')
</template>

<script>
	import Botao from '../components/atoms/Botoes'
	import UsuarioService from '../services/usuarioService';
	export default {
	name: 'Login',
	components:{
		Botao
	},
	data(){
		return {
			user:{
				login:'',
				senha:''
			},
			loginRule:[
				v => !!v || 'Username é obrigátorio',
				v => (v && v.length >= 4 && v.length <= 12  ) || 'Username deve conter entre 4 e 12 caracteres'  
			],
			senhaRule:[
				v => !!v || 'senha é obrigátoria',
				v => (v  && v.length >= 5 && v.length <= 15) || 'Senha deve conter entre 5 e 15 caracteres'
			],

		}
	},
	computed:{
		isDisabled: function(){
			return this.desabilitarBotaoSalvar();
		},
	},
	methods:{
		logar(){
			UsuarioService.logar(this.user).then(() => {
				localStorage.setItem('nome', this.user.login);
				sessionStorage.setItem('nome', this.user.login);
				this.$router.push("/");
			}).catch(() => {
				this.$toast.error("Erro ao realizar o login");
			});
		},
		verificarUsuario(){
			if(!(localStorage.nome === undefined || localStorage.nome=== null)){
				
				this.$router.push("/");
			}
		},
		cadastrarUsuario(){
			this.$router.push('/cadastroUsuario');
		},
		desabilitarBotaoSalvar(){
			if(this.user.login === "" || this.user.login.length < 4 || this.user.login.length > 12) return true;
			if(this.user.senha === "" || this.user.senha.length < 5 || this.user.senha.length > 15) return true;
			return false;
		}
	},
	mounted(){
		this.verificarUsuario()
	}

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	.login{
		margin-top: 10%;
		font-size: 1.3rem;
		text-align:center;

	}
</style>
