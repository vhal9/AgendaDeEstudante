package com.AgendaDeEstudantes.backend.controllers;

import com.AgendaDeEstudantes.backend.models.Etiqueta;
import com.AgendaDeEstudantes.backend.models.Usuario;
import com.AgendaDeEstudantes.backend.services.EtiquetaService;
import com.AgendaDeEstudantes.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etiqueta")
public class EtiquetaController {
    @Autowired
    EtiquetaService etiquetaService;

    @Autowired
    UsuarioService usuarioService;

    @CrossOrigin
    @GetMapping("/listar")
    public ResponseEntity<List> listar(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent() && user.get().getSenha().equals(senha)) {
            return new ResponseEntity<>(etiquetaService.listar(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha,@RequestBody Etiqueta etiqueta){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent() && user.get().getSenha().equals(senha)) {
            try {
                etiqueta.setUsuario(user.get());
                etiquetaService.salvar(etiqueta);
                return new ResponseEntity<>("Etiqueta salva com sucesso!", HttpStatus.CREATED);
            }catch (Exception ex){
                return new ResponseEntity<>("Erro ao salvar!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Nao autorizado!", HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @PutMapping("/alterar")
    public ResponseEntity<String> alterar(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha, @RequestBody Etiqueta etiqueta){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent() && user.get().getSenha().equals(senha)) {
            try {
                etiquetaService.alterar(etiqueta);
                return new ResponseEntity<>("Etiqueta alterada com Sucesso!", HttpStatus.OK);
            }catch (Exception ex){
                return new ResponseEntity<>("Erro ao alterar!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Nao autorizado!", HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha, @PathVariable Long id){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent() && user.get().getSenha().equals(senha)) {
            if(etiquetaService.excluir(id)){
                return new ResponseEntity<>("Etiqueta excluida com Sucesso!", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Erro ao excluir!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Nao autorizado!", HttpStatus.UNAUTHORIZED);
    }

//    @CrossOrigin
//    @GetMapping("/get/{id}")
//    public ResponseEntity<Optional> get(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha, @PathVariable Long id) {
//        Optional<Usuario> user = usuarioService.getUsuario(login);
//        if( user.isPresent() && user.get().getSenha().equals(senha)) {
//            return new ResponseEntity<>(etiquetaService.getEtiqueta(id), HttpStatus.OK);
//        }
//        Optional<Etiqueta> etiqueta = null;
//        return new ResponseEntity<Optional>(etiqueta, HttpStatus.UNAUTHORIZED);
//    }



}
