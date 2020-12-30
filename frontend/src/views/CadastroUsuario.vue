<template lang="pug">
	.container.user
		h2 Bem vindo 
		v-form(ref='form', lazy-validation)
			v-text-field(v-model='user.nome' label='Nome' :rules='nomeRule')
			v-text-field(v-model='user.login' label='Username' :rules='loginRule')
			v-text-field(v-model='user.senha' label='Senha' type='password' :rules='senhaRule')
			Botao(label='Voltar', @clickBotao='voltarPagina', )
			Botao(label='Cadastrar', @clickBotao='salvarUsuario', :disabled='isDisabled')
</template>

<script>
	import Botao from '../components/atoms/Botoes';
	import UsuarioService from '../services/usuarioService';
	export default {
		name: 'CadastroUsuario',
		components:{
			Botao	
		},
		data(){
			return {
				user:{
					id:null,
					nome:'',
					login:'',
					senha:''
				},

				nomeRule:[
					v => !!v || 'Nome é obrigátorio',
					v => (v && v.length >= 3 && v.length <= 20  ) || 'Username deve conter entre 3 e 20 caracteres'  
				],
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
			salvarUsuario(){
				UsuarioService.salvar(this.user)
				.then(resposta => {
					this.$toast.success(resposta.data);
					this.limpar();
					setTimeout(() => {
						this.$router.push("/login");
					}, 2000);
					
				}).catch(error => {
					this.$toast.error(error);
				});
			},
			desabilitarBotaoSalvar(){
				if(this.user.nome === "" || this.user.nome.length < 3 ||  this.user.nome.length > 20) return true;
				if(this.user.login === "" || this.user.login.length < 4 ||  this.user.login.length > 12) return true;
				if(this.user.senha === "" || this.user.senha.length < 5 ||  this.user.senha.length > 15) return true;
				return false;
			},
			limpar(){
				this.user = {
					id:null,
					nome:'',
					login:'',
					senha:''
					}
			},
			voltarPagina(){
				this.$router.push("/login");
			}

		}
	}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	.user{
		margin-top: 10%;
		font-size: 1.3rem;
		text-align:center;

	}
</style>
