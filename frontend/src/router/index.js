import Vue from 'vue';
import Router from 'vue-router';

import Login from '../views/Login';
import Home from '../views/Home';
import CadastroTarefa from '../views/CadastroTarefa';
import CadastroEtiqueta from '../views/CadastroEtiqueta';
import CadastroUsuario from '../views/CadastroUsuario';



Vue.use(Router);

const routes = [
    {
        name: Login,
        path: '/login',
        component: Login,
    },
    {
        name: Home,
        path: '/',
        component: Home,
    },
    {
        name: CadastroTarefa,
        path:'/cadastroTarefa',
        component: CadastroTarefa
    },
    {
        name: CadastroEtiqueta,
        path:'/cadastroEtiqueta',
        component: CadastroEtiqueta
    },
    {
        name: CadastroUsuario,
        path:'/cadastroUsuario',
        component: CadastroUsuario
    }
]

const router = new Router ({routes})

export default router;