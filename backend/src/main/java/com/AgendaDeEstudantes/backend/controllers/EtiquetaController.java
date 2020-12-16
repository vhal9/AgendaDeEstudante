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
    public ResponseEntity<List> listar(@RequestHeader(value="login") String login){

        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent()) {
            return new ResponseEntity<>(etiquetaService.listar(user.get().getId()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestHeader(value="login") String login, @RequestBody Etiqueta etiqueta){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent()) {
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
    public ResponseEntity<String> salvar(@RequestBody Etiqueta etiqueta){
        try {
            Optional<Usuario> user = usuarioService.getUsuario("a");
            etiqueta.setUsuario(user.get());
            etiquetaService.salvar(etiqueta);
            return new ResponseEntity<>("Etiqueta salva com sucesso!", HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>("Erro ao salvar!", HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin
    @PutMapping("/alterar")
    public ResponseEntity<String> alterar(@RequestHeader(value="login") String login, @RequestBody Etiqueta etiqueta){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent() ) {
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
    public ResponseEntity<String> excluir(@RequestHeader(value="login") String login, @PathVariable Long id){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent() ) {
            if(etiquetaService.excluir(id)){
                return new ResponseEntity<>("Etiqueta excluida com Sucesso!", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Erro ao excluir!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Nao autorizado!", HttpStatus.UNAUTHORIZED);
    }


}
