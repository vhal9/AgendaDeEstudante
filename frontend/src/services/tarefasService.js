import {ApiService} from './api.service'


const TarefaService =  {
    listar: async() => {
        return ApiService.get('/tarefa/listar/', {
            headers:{
                login: localStorage.nome
            }
        });
    },
    salvar: async(tarefa) => {
        return ApiService.post('/tarefa/salvar/', tarefa, {
            headers:{
                login: localStorage.nome
            }
        });
    },
    alterar: async(tarefa) =>{
        return ApiService.put('tarefa/alterar', tarefa, {
            headers:{
                login: localStorage.nome
            }
        });
    },
    excluir: async(id) =>{
        return ApiService.delete(`/tarefa/excluir/${id}`, {
            headers:{
                login: localStorage.nome
            }
        });
    }

}
export default TarefaService;