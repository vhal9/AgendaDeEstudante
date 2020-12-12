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
    public ResponseEntity<Login> salvarUsuario(@RequestBody Usuario usuario){
        if(usuarioService.getUsuario(usuario.getLogin()).isPresent()){
            return new ResponseEntity<>(new Login(), HttpStatus.FORBIDDEN);
        }
        try{
            Usuario user = usuarioService.salvarUsuario(usuario);
            Login newLogin = new Login(user.getLogin(), user.getSenha());
            return new ResponseEntity<>(newLogin, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new Login(), HttpStatus.BAD_REQUEST);
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
