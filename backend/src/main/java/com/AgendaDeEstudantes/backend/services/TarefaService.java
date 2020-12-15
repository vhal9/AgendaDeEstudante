package com.AgendaDeEstudantes.backend.services;

import com.AgendaDeEstudantes.backend.models.Tarefa;
import com.AgendaDeEstudantes.backend.repositorys.EtiquetaRepository;
import com.AgendaDeEstudantes.backend.repositorys.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

//    public List<Tarefa> listar(Long id){
//        List<Tarefa> tarefas = tarefaRepository.findAllByUsuario_Id(id);
//        for(Tarefa tarefa: tarefas){
//            tarefa.getUsuario().setSenha("");
//        }
//        return tarefas;
//    }
    public List<Tarefa> listar(){
        return tarefaRepository.findAllByUsuario_IdOrderByData(70l);
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        etiquetaRepository.save(tarefa.getEtiqueta());
        return tarefaRepository.save(tarefa);
    }

    public Tarefa alterarTarefa(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    public boolean excluirTarefa(Long id){
        int count = (int) tarefaRepository.count();
        tarefaRepository.deleteById(id);
        return (tarefaRepository.count() < count);
    }

    public Optional<Tarefa> getTarefa(Long id){
        return tarefaRepository.findById(id);
    }
}
