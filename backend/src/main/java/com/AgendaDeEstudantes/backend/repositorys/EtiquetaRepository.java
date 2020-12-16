package com.AgendaDeEstudantes.backend.repositorys;

import com.AgendaDeEstudantes.backend.models.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
    List<Etiqueta> findAllByUsuario_Id(Long id);
}
