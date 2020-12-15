<template>
  <div>
    <b-navbar class="header" type="light" variant="warning">
        <b-navbar-brand > <b-icon icon="person-circle" ></b-icon> Agenda de Estudante</b-navbar-brand>
        <b-navbar-nav>
        <b-nav-item to="/">Home</b-nav-item>
        <b-nav-item to="/cadastroTarefa">Cadastrar Tarefa</b-nav-item>
        <b-nav-item to="/cadastroEtiqueta">Cadastrar Etiqueta</b-nav-item>
        <b-nav-item href="#" disabled>Victor Hugo</b-nav-item>
        <b-nav-item to="/login"><b-icon icon="power"> </b-icon> Logout</b-nav-item>
        <!-- Navbar dropdowns -->
        
        </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: 'Menu',
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .header{
    min-height : 80px;
  }
</style>
