package com.AgendaDeEstudantes.backend.repositorys;

import com.AgendaDeEstudantes.backend.models.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
}
