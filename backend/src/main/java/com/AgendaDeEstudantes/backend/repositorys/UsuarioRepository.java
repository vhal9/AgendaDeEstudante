package com.AgendaDeEstudantes.backend.repositorys;

import com.AgendaDeEstudantes.backend.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findFirstByLogin(String login);
}
