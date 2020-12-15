import {ApiService} from './api.service'


const TarefaService =  {
    listar: async() => {
        return ApiService.get('/tarefa/listar/');
    },
    salvar: async(tarefa) => {
        return ApiService.post('/tarefa/salvar/', tarefa);
    },
    alterar: async(tarefa) =>{
        return ApiService.put('tarefa/alterar', tarefa);
    },
    excluir: async(id) =>{
        return ApiService.delete(`/tarefa/excluir/${id}`);
    }

}
export default TarefaService;