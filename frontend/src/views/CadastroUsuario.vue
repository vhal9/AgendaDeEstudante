<template>
  
    <div class="container">

        <div class="titulo">
            <h2>Bem vindo | Cadastro</h2>
        </div>
        <div class="form">
            <div class="col-md-5" >
                <b-form-input v-model="user.nome" placeholder="nome"></b-form-input>
            </div>
            <div class="col-md-5" >
                <b-form-input v-model="user.login" placeholder="login"></b-form-input>
            </div>
            <div class="col-md-5">
                <b-form-input v-model="user.senha" placeholder="senha" type="password"></b-form-input>
            </div>
              
              
        </div>
      
      <div class="botoes">
          <div class="col-md-1">
              <b-button variant="warning" class="link" to="/login" > Voltar </b-button>
          </div>
          <div class="col-md-1">
              <b-button variant="warning" @click="salvarUsuario" :disabled='isDisabled'> Cadastrar </b-button>
          </div>
      </div>
    </div>
</template>

<script>
  import UsuarioService from '../services/usuarioService';
  export default {
    name: 'CadastroUsuario',
    data(){
        return {
            user:{
                id:null,
                nome:'',
                login:'',
                senha:''
              }
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
            if(this.user.nome === "") return true;
            if(this.user.login === "") return true;
            if(this.user.senha === "") return true;
        },
        limpar(){
            this.user = {
                id:null,
                nome:'',
                login:'',
                senha:''
              }
        }

    }

    
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .container{
        background-color: #ffff99;
        margin-top: 10%;
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
