<template lang="pug">
    #header
        v-system-bar(height='50%',window, dark, color='#3949AB')
            .logo
                v-icon.icone1 mdi-account-circle
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
				localStorage.removeItem("nome");
				this.$router.push("/login");
            },
        },
        
    }
</script>
<style scoped>
    #header{
        position: fixed;
        top:0;
        right: 0;
        left: 0;
    }
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
    .icone1{
        margin-bottom: 2%;
    }
    
    span{
        font-size: 1.1rem;
    }
    .logo{
        margin-left: 1%;
    }
</style>