<template lang="pug">
	#formulario.container
		h3 Cadastrar Etiqueta</h2>
		v-form(ref='form', lazy-validation)
			v-text-field(v-model="etiqueta.nome" label="Nome" :rules='nomeRule')
			Botao(label="Limpar", @clickBotao='limpar')
			Botao(label="Salvar", :disabled="isDisabled", @clickBotao='salvarEtiqueta')

</template>

<script>
	import Botao from '../components/atoms/Botoes';
	import EtiquetaService from '../services/etiquetaService';
	export default {
		name: 'CadastroEtiqueta',
		components:{
			Botao,
		},
		data(){
			return {
				etiqueta:{
					id:null,
					nome:'',
				},
				nomeRule:[
					v => !!v || 'Nome é obrigátorio',
					v => (v && v.length >= 3 && v.length <= 10  ) || 'Nome deve conter entre 3 e 10 caracteres'  
				],
			}
		},
		computed:{
			isDisabled: function(){
				return this.desabilitarBotaoSalvar();
			},
		},
		methods:{
			salvarEtiqueta(){
				EtiquetaService.salvar(this.etiqueta)
				.then(resposta => {
					if(resposta.status === 201){
						this.$toast.success(resposta.data);
						this.limpar();
					}
					
				}).catch(error => {
					this.$toast.error(error);
				});
			},
			desabilitarBotaoSalvar(){
				if(this.etiqueta.nome === "") return true;
				if(this.etiqueta.nome.length < 3 || this.etiqueta.nome.length > 10) return true;
				return false; 
			},
			limpar(){
				this.$refs.form.reset()
				this.etiqueta.id = null;
				this.etiqueta.nome = ''
			},
			verificarLogin(){
				if(localStorage.nome === undefined || localStorage.nome === null){
					this.$router.push("/login")
				}
			}

		},
		mounted(){
			this.verificarLogin();
		}

	}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	#formulario{
		margin-top: 10%;
		font-size: 1.3rem;
		text-align: center;
	}
</style>
