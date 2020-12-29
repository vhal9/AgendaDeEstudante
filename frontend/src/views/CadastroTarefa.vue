<template lang="pug">
	#formulario.container
		h3 Cadastro de Tarefas
		v-form(ref='form', lazy-validation)
			v-text-field(v-model='tarefa.titulo' label='Título' :rules='tituloRule')
			v-text-field(v-model='tarefa.descricao' label='Descrição' :rules='descricaoRule')
			v-text-field(v-model='tarefa.data' label='Data' type='date' :rules='dataRule')
			v-select(v-model='tarefa.etiqueta' :items='etiquetas' label='Etiqueta' :rules='etiquetaRule')
			Botao(label="Limpar", @clickBotao='limpar')
			Botao(label="Salvar", :disabled="isDisabled", @clickBotao='salvarTarefa')
			
		
</template>

<script>
import EtiquetaService from '../services/etiquetaService';
import TarefaService from '../services/tarefasService';
import Botao from '../components/atoms/Botoes';

const clone = require("lodash.clone");
	export default {
    name: 'CadastroTarefa',
    components:{
		Botao
	},
	data(){
		return {
			tarefa:{
				id:null,
				titulo:'',
				descricao:'',
				data:'',
				etiqueta:null,
				usuario:{
					id: null
				}
			},
			etiquetas:[{ value: null, text: 'Etiqueta' },],
			msg:'',
			tituloRule:[
				v => !!v || 'Título é obrigátorio',
                v => (v && v.length >= 3 && v.length <= 20  ) || 'Título deve conter entre 3 e 20 caracteres'  
			],
			descricaoRule:[
				v => !!v || 'Descrição é obrigátoria',
				v => (v  && v.length >= 5 && v.length <= 30) || 'Descrição deve conter entre 5 e 30 caracteres'
			],
			dataRule:[
				v => !!v || 'Data é obrigátoria',
			],
			etiquetaRule:[
				v => !!v  || 'Etiqueta é obrigatória'
			]
		}
	},
    computed:{
        isDisabled: function(){
            return this.desabilitarBotaoSalvar();
        },
    },
    methods:{
        salvarTarefa(){
            let tarefaSalvar = clone(this.tarefa);
            tarefaSalvar.etiqueta = clone({'id': this.tarefa.etiqueta})
            TarefaService.salvar(tarefaSalvar).then(resposta => {
                if(resposta.status === 201){
                    this.$toast.success(resposta.data);
                    this.limpar();
                }
                
            }).catch(error => {
                this.$toast.error(error);
            });
        },
        desabilitarBotaoSalvar(){
            if(this.tarefa.titulo === "") return true;
            if(this.tarefa.descricao === "") return true;
            if(this.tarefa.data === "") return true;
            if(this.tarefa.etiqueta === null) return true;
			return false;

        },
        listarEtiquetas(){
            EtiquetaService.listar().then(resposta =>{
                let value;
                let text;
                resposta.data.forEach(element => {
                    value = element.id;
                    text = element.nome;
                    this.etiquetas.push({'value': value, 'text':text});
                }); 
            })
        },
        limpar(){
            console.log('teste2')
			this.$refs.form.reset()
            this.tarefa ={
                id:null,
                titulo:'',
                descricao:'',
                data:'',
                etiqueta:null,
                usuario:{
                    id: null
                }
			}
			
        },
        verificarLogin(){
            if(localStorage.nome === undefined || localStorage.nome === null){
                this.$router.push("/login")
            }
        }
        

    },
    mounted(){
		this.verificarLogin();
        this.listarEtiquetas();
    },
  
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
