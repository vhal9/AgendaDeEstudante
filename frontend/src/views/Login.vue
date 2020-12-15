<template>
  <div class="container">
      <div class="titulo">
          <h2>Bem vindo | Login</h2>
      </div>
      <div class="form">
          <div class="col-md-5" >
              <b-form-input v-model="user.login" placeholder="usuario"></b-form-input>
          </div>
          <div class="col-md-5">
              <b-form-input v-model="user.senha" placeholder="senha" type="password"></b-form-input>
          </div>
      </div>
     
    <div class="botoes">
        <div class="col-md-1">
            <b-button variant="warning" > Entrar </b-button>
        </div>
        <div class="col-md-1">
            <b-button variant="warning" class="link" to="/CadastroUsuario"> Cadastrar </b-button>
        </div>
    </div>
  </div>
</template>

<script>
    export default {
    name: 'Login',
    data(){
        return {
            user:{
                login:'',
                senha:''
            }
        }
    },
    methods:{
        teste(){
            console.log(localStorage.login);
        }
    },
    mounted(){
        this.teste();
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
