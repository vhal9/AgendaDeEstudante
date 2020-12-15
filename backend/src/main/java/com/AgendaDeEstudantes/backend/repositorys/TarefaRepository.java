package com.AgendaDeEstudantes.backend.repositorys;

import com.AgendaDeEstudantes.backend.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAllByUsuario_IdOrderByData(Long id);

}
