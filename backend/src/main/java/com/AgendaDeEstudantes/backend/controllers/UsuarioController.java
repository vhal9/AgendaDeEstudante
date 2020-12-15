package com.AgendaDeEstudantes.backend.controllers;

import com.AgendaDeEstudantes.backend.models.Login;
import com.AgendaDeEstudantes.backend.models.Usuario;
import com.AgendaDeEstudantes.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping("/login")
    public ResponseEntity<String>  veriricarLogin(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha){
        Login newLogin = new Login(login, senha);
        if(usuarioService.verificarAutorizacao(newLogin)){
            return new ResponseEntity<>("Autenticaçao confirmada", HttpStatus.OK);
        }
        return new ResponseEntity<>("Autentiçao nao confirmada", HttpStatus.UNAUTHORIZED);
    }
}
