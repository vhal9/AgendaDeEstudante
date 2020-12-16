import {ApiService} from './api.service'


const UsuarioService =  {
    salvar: async(usuario) => {
        return ApiService.post('/usuario/salvar/', usuario);
    },
    logar: async(login) => {
        return ApiService.post('/usuario/logar', login);
    },
    getNomeUsuario: async() => {
        return ApiService.get('/usuario/getUsuario',{
            headers:{
                login: localStorage.nome
            }
        });
    }

}
export default UsuarioService;
