<template lang="pug">
    #header
        v-system-bar(height='50%', window, dark, color='#363636')
            .logo
                v-icon mdi-account-circle
                span Agenda de Estudante
            v-spacer
            span(@click='logout')
                a Logout 
                    v-icon.icone mdi-power 
</template>

<script>
    export default {
        name: "Header",
        methods:{
            logout() {
				console.log("testando");
				localStorage.removeItem("nome");
				console.log("removido");
				this.$router.push("/login");
			},
        }
    }
</script>
<style scoped>
    a{
        text-decoration: none !important;
        color: white !important;   
        font-size: 1rem;
    }
    a:hover{
        color: gray !important;
    }
    .icone{
        margin-bottom: 3%;
    }
    
    span{
        font-size: 1.1rem;
    }
    .logo{
        margin-left: 1%;
    }
</style>