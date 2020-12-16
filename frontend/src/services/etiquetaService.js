import {ApiService} from './api.service'


const EtiquetaService =  {
    listar: async() => {
        return ApiService.get('/etiqueta/listar/', {
            headers:{
                login: localStorage.nome
            }
        });
    },
    salvar: async(etiqueta) => {
        return ApiService.post('/etiqueta/salvar/', etiqueta, {
            headers:{
                login: localStorage.nome
            }
        });
    }

}
export default EtiquetaService;