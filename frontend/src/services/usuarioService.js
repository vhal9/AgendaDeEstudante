import {ApiService} from './api.service'


const UsuarioService =  {
    salvar: async(usuario) => {
        return ApiService.post('/usuario/salvar/', usuario);
    }

}
export default UsuarioService;
