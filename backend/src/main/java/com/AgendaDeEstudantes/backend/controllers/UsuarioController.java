package com.AgendaDeEstudantes.backend.controllers;

import com.AgendaDeEstudantes.backend.models.Login;
import com.AgendaDeEstudantes.backend.models.Usuario;
import com.AgendaDeEstudantes.backend.services.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/usuario/")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @CrossOrigin
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarUsuario(@RequestBody Usuario usuario){
        if(usuarioService.getUsuario(usuario.getLogin()).isPresent()){
            return new ResponseEntity<>("Login ja existente!", HttpStatus.BAD_REQUEST);
        }
        try{
            Usuario user = usuarioService.salvarUsuario(usuario);
            return new ResponseEntity<>("Usuario Cadastrado com Sucesso!", HttpStatus.ACCEPTED);
        }catch (Exception ex){
            return new ResponseEntity<>("Erro ao cadastrar!", HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin
    @PostMapping("/logar")
    public ResponseEntity<String>  veriricarLogin(@RequestBody Login newLogin){
        if(usuarioService.verificarAutorizacao(newLogin)){
            return new ResponseEntity<>("Autenticaçao confirmada", HttpStatus.OK);
        }
        return new ResponseEntity<>("Autentiçao nao confirmada", HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @GetMapping("/getUsuario")
    public ResponseEntity<String> getUsuarioByID(@RequestHeader(value="login") String login) {
        Optional<Usuario> userLogin = usuarioService.getUsuario(login);
        if (userLogin.isPresent()){
            return new ResponseEntity<String>(userLogin.get().getNome(), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<String>("Erro ao obter nome do usuario", HttpStatus.FORBIDDEN);
        }
    }
}
