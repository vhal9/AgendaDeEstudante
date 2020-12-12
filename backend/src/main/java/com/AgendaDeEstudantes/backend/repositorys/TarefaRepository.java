package com.AgendaDeEstudantes.backend.repositorys;

import com.AgendaDeEstudantes.backend.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    //@Query(value = "SELECT t FROM tb_tarefa t  WHERE t.tb_usuario.id_usuario = :id", nativeQuery = true)
    //@Query(value = "FROM Tarefas t  WHERE t.Usuario.id = :id")
    List<Tarefa> findAllByUsuario_Id(Long id);

}
