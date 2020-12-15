<template>
    <div class="container">

        <div class="titulo">
            <h2>Cadastro de Tarefa</h2>
        </div>
        <div class="form">
            <div class="col-md-5" >
                <b-form-input v-model="tarefa.titulo" placeholder="Título"></b-form-input>
            </div>
            <div class="col-md-5" >
                <b-form-input v-model="tarefa.descricao" placeholder="Descrição"></b-form-input>
            </div>
            <div class="col-md-5">
                <b-form-input v-model="tarefa.data"  type="date"></b-form-input>
            </div>
            <div class="col-md-5">
                <b-form-select v-model="tarefa.etiqueta" :options="etiquetas" placeholder="Etiqueta"></b-form-select>
            </div>


              
        </div>
      
      <div class="botoes">
          <div class="col-md-1">
              <b-button variant="warning" class="link" to="/" > Voltar </b-button>
          </div>
          <div class="col-md-1">
              <b-button variant="warning" @click="salvarTarefa" :disabled='isDisabled'> Cadastrar </b-button>
          </div>
      </div>
    </div>
</template>

<script>
import EtiquetaService from '../services/etiquetaService';
import TarefaService from '../services/tarefasService';
export default {
  name: 'CadastroTarefa',
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
          }
    },
    computed:{
        isDisabled: function(){
            return this.desabilitarBotaoSalvar();
        },
    },
    methods:{
        salvarTarefa(){
            
            this.tarefa.etiqueta = {'id': this.tarefa.etiqueta}
            TarefaService.salvar(this.tarefa).then(resposta => {
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
            if(this.tarefa.nome === "") return true;

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
        }
        

    },
    mounted(){
        this.listarEtiquetas();
    }
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .container{
        background-color: #ffff99;
        margin-top: 2%;
        min-height: 300px;
    }
    .titulo{
        margin-top: 2%;
        padding: 10px;
    }
    .form{
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 2%;
    }
    .botoes{
        display: flex;
        flex-direction: row;
        justify-content:center;
        margin-top: 2%;
    }
    .link{
        color:black;
    }
</style>
