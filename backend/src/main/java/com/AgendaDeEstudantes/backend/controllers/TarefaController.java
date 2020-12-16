package com.AgendaDeEstudantes.backend.controllers;

import com.AgendaDeEstudantes.backend.models.Etiqueta;
import com.AgendaDeEstudantes.backend.models.Tarefa;
import com.AgendaDeEstudantes.backend.models.Usuario;
import com.AgendaDeEstudantes.backend.services.EtiquetaService;
import com.AgendaDeEstudantes.backend.services.TarefaService;
import com.AgendaDeEstudantes.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/tarefa/")
public class TarefaController {
    @Autowired
    TarefaService tarefaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EtiquetaService etiquetaService;

    @CrossOrigin
    @GetMapping("/listar")
    public ResponseEntity<List> listar(@RequestHeader(value="login") String login){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent()) {
            return new ResponseEntity<>(tarefaService.listar(user.get().getId()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
    }
    @CrossOrigin
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarTarefa(@RequestHeader(value="login") String login, @RequestBody Tarefa tarefa) {
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent()) {
            try {
                Optional<Etiqueta> etiqueta = etiquetaService.getEtiqueta(tarefa.getEtiqueta().getId());
                tarefa.setUsuario(user.get());
                tarefa.setEtiqueta(etiqueta.get());
                tarefaService.salvarTarefa(tarefa);
                return new ResponseEntity<>("Tarefa salva com sucesso!", HttpStatus.CREATED);
            }catch (Exception ex){
                return new ResponseEntity<>("Erro ao salvar!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Nao Autorizado", HttpStatus.UNAUTHORIZED);


    }

    @CrossOrigin
    @PutMapping("/alterar")
    public ResponseEntity<String> alterarTarefa(@RequestHeader(value="login") String login, @RequestBody Tarefa tarefa){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent() ) {
            try {
                Optional<Etiqueta> etiqueta = etiquetaService.getEtiqueta(tarefa.getEtiqueta().getId());
                tarefa.setUsuario(user.get());
                tarefa.setEtiqueta(etiqueta.get());
                tarefaService.alterarTarefa(tarefa);
                return new ResponseEntity<>("Tarefa alterada com sucesso!", HttpStatus.OK);
            }catch (Exception ex){
                return new ResponseEntity<>("Erro ao alterar!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Nao Autorizado", HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @DeleteMapping("/excluir/{id}")
        public ResponseEntity<String> excluirTarefa(@RequestHeader(value="login") String login, @PathVariable Long id){
        Optional<Usuario> user = usuarioService.getUsuario(login);
        if( user.isPresent() ) {
            if(tarefaService.excluirTarefa(id)){
                return new ResponseEntity<>("Tarefa excluida com sucesso!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Erro ao excluir!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Nao Autorizado", HttpStatus.UNAUTHORIZED);
    }


}
