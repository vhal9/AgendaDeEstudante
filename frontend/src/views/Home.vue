<template>
  <div class="container">
      <v-container>
        <div class="coluna">
            <ul  class="coluna">
                <li v-for="tarefa in tarefas" :key="tarefa.id">
                    <v-card
                        color="#ffff99"
                        class="card-home"
                        
                    >
                        <v-card-title class="headline">
                            {{tarefa.titulo}}
                        </v-card-title>

                        <v-card-text>{{tarefa.descricao}}</v-card-text>
                        <v-card-text>{{formatarData(tarefa.data)}} | {{tarefa.etiqueta.nome}}</v-card-text>
                        <v-divider></v-divider>
                        <v-card-actions class="botoes">
                            <v-btn  text @click="editar(tarefa)">
                                <b-icon icon="pencil-square"></b-icon>
                            </v-btn>
                            <v-btn  text @click="excluirTarefa(tarefa.id)">
                                <b-icon icon="trash"></b-icon>
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </li>
            </ul>
      </div>
    </v-container>

    <v-row justify="center">
        <v-dialog
            v-model="dialog"
            persistent
            max-width="600px"
            >
            <v-card>
                <v-card-title>
                    <span class="headline">Editando Tarefa </span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12" sm="12" md="12" >
                                <v-text-field label="Título" v-model="tarefaEdicao.titulo"></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="12" md="12" >
                                <v-text-field label="Descrição" v-model="tarefaEdicao.descricao" ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="12" md="12" >
                                <v-text-field label="Data" type="date" v-model="tarefaEdicao.data" ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="12" md="12" >
                                <v-select v-model="tarefaEdicao.etiqueta.nome" :items="etiquetas" label="Etiqueta"  ></v-select>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="warning "  @click="cancelar" > Cancelar </v-btn>
                    <v-btn color="warning" :disabled="isDisabled"  @click="salvar" > Salvar </v-btn>
                </v-card-actions>
            </v-card>
            </v-dialog>
        </v-row>

     

  </div>
</template>

<script>
    import TarefasService from '../services/tarefasService';
    import EtiquetaService from '../services/etiquetaService';
    import moment from 'moment';
    const clone = require("lodash.clone");
    export default {
        name: 'Home',
        data(){
            return {
                tarefas:[],
                tarefaEdicao:{
                    titulo:'',
                    descricao:'',
                    data:'',
                    etiqueta:{
                        id:null,
                        nome:""
                    },
                },
                etiquetas:[],
                dialog: false,
            }
        },
        computed:{
            isDisabled: function(){
                return this.desabilitarBotaoSalvar();
            }
        },
        methods:{
            listar(){
                TarefasService.listar()
                .then(resposta => {
                    this.tarefas = resposta.data;
                })
            },
            formatarData(data){
                return moment(String(data)).format('DD/MM/YYYY');
            },
            formatarDataInput(data){
                return moment(String(data)).format('YYYY-MM-DD');
            },
            excluirTarefa(id){
                TarefasService.excluir(id).then(resposta =>{
                    this.$toast.success(resposta.data)
                    this.listar();
                }).catch(error => {
                    this.$toast.error(error);
                })
            },
            listarEtiquetas(){
                EtiquetaService.listar().then(resposta =>{
                    let value;
                    let text;
                    resposta.data.forEach(element => {
                        value = element.id;
                        text = element.nome;
                        this.etiquetas.push({'id':value,'text':text});
                    }); 
                })
            },
            cancelar(){
                this.tarefaEdicao = {
                    titulo:'',
                    descricao:'',
                    data:'',
                    etiqueta: {
                        id:null,
                        nome:""
                    }
                }
                this.dialog = false;
            },
            salvar(){
                console.log(this.tarefaEdicao);
                this.tarefaEdicao.etiqueta = this.etiquetas.find(element => element.text === this.tarefaEdicao.etiqueta.nome );
                TarefasService.alterar(clone(this.tarefaEdicao)).then(resposta=>{
                    this.$toast.success(resposta.data);
                    this.dialog = false;
                    this.listar();
                })

            },
            editar(item){
                this.tarefaEdicao = clone(item);
                this.tarefaEdicao.etiqueta = clone(item.etiqueta);
                this.tarefaEdicao.data = this.formatarDataInput(this.tarefaEdicao.data);
                this.dialog = true;
            },
            desabilitarBotaoSalvar(){
                if(this.tarefaEdicao.titulo === "") return true;
                if(this.tarefaEdicao.descricao === "") return true;
                if(this.tarefaEdicao.data === "") return true;
            }


        },
        mounted(){
            this.listar();
            this.listarEtiquetas();
        }
        
        
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .container{
        align-items: center;
    }
    .coluna{
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        grid-column-gap: 15%;
        grid-row-gap: 5%;
        
        list-style: none;
    }
    .card-home{
        margin-top: 0%;
        margin-bottom: 2%;
        min-width: 250px;
    }
    .botoes{
        display: flex;
        flex-direction: row;
        justify-content: center;
    }
</style>
