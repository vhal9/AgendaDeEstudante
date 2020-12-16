package com.AgendaDeEstudantes.backend.services;

import com.AgendaDeEstudantes.backend.models.Login;
import com.AgendaDeEstudantes.backend.models.Usuario;
import com.AgendaDeEstudantes.backend.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;


    public Optional<Usuario> getUsuario(String login){
        return usuarioRepository.findFirstByLogin(login);
    }
    public Optional<Usuario> getUsuarioByID(Long id){
        return usuarioRepository.findById(id);
    }

    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Boolean verificarAutorizacao(Login login){
        Optional<Usuario> user = this.getUsuario(login.getLogin());
        return user.isPresent() && user.get().getSenha().equals(login.getSenha());
    }

}
