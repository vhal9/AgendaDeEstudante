<template>
    <div class="container">
        <div class="titulo">
            <h2>Cadastrar Etiqueta</h2>
        </div>
        <div class="form">
            <div class="col-md-5" >
                <b-form-input v-model="etiqueta.nome" placeholder="nome"></b-form-input>
            </div>
              
        </div>
      
      <div class="botoes">
          <div class="col-md-1">
              <b-button variant="warning" class="link" to="/"> Voltar </b-button>
          </div>
          <div class="col-md-1">
              <b-button variant="warning" @click="salvarEtiqueta" :disabled='isDisabled'> Cadastrar </b-button>
          </div>
      </div>
    </div>
</template>

<script>
    import EtiquetaService from '../services/etiquetaService';
    export default {
        name: 'CadastroEtiqueta',
        data(){
            return {
                etiqueta:{
                    id:null,
                    nome:'',
                },
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
            },
            limpar(){
                this.etiqueta.id = null;
                this.etiqueta.nome = ''
            }

        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .container{
        background-color: #ffff99;
        margin-top: 5%;
        min-height: 300px;
        align-items: center;
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
