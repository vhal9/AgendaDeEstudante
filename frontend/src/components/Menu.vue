<template>
  <div>
    <b-navbar class="header" type="light" variant="warning" >
        <b-navbar-brand > <b-icon icon="person-circle" ></b-icon> Agenda de Estudante</b-navbar-brand>
        <b-navbar-nav class="col-md-12">
        <b-nav-item  to="/">Home</b-nav-item>
        <b-nav-item  to="/cadastroTarefa">Cadastrar Tarefa</b-nav-item>
        <b-nav-item   to="/cadastroEtiqueta">Cadastrar Etiqueta</b-nav-item>
        <b-nav-item class="identificador" href="#" disabled >{{nomeUsuario}}</b-nav-item>
        <b-nav-item  @click="logout"><b-icon icon="power" > </b-icon> Logout</b-nav-item>
        <!-- Navbar dropdowns -->
        
        </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
import UsuarioService from '../services/usuarioService';
export default {
  name: 'Menu',
  data(){
    return{
      nomeUsuario: ''
    }
  },
  methods:{
      logout(){
          console.log("testando");
          localStorage.removeItem('nome');
          console.log("removido")
          this.$router.push("/login");
      },
      getNomeUsuario(){
          UsuarioService.getNomeUsuario().then(resposta =>{
              this.nomeUsuario = resposta.data;
          })
      }

  },
  mounted(){
      this.getNomeUsuario();
  }
  
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .header{
    min-height : 80px;
  }
  .identificador{
      margin-left: 30%;
  }
</style>
