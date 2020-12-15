import {ApiService} from './api.service'


const EtiquetaService =  {
    listar: async() => {
        return ApiService.get('/etiqueta/listar/');
    },
    salvar: async(etiqueta) => {
        return ApiService.post('/etiqueta/salvar/', etiqueta);
    }

}
export default EtiquetaService;