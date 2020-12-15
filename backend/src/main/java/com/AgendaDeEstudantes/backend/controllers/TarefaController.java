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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    //public ResponseEntity<List> listar(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha){
    public ResponseEntity<List> listar(){
//        Optional<Usuario> user = usuarioService.getUsuario(login);
//        if( user.isPresent() && user.get().getSenha().equals(senha)) {
//            return new ResponseEntity<>(tarefaService.listar(user.get().getId()), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(tarefaService.listar(), HttpStatus.OK);
    }
//    @CrossOrigin
//    @PostMapping("/salvar")
//    public ResponseEntity<String> salvarTarefa(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha, @RequestBody Tarefa tarefa) {
//        Optional<Usuario> user = usuarioService.getUsuario(login);
//        if( user.isPresent() && user.get().getSenha().equals(senha)) {
//            try {
//                Optional<Etiqueta> etiqueta = etiquetaService.getEtiqueta(tarefa.getEtiqueta().getId());
//                tarefa.setUsuario(user.get());
//                tarefa.setEtiqueta(etiqueta.get());
//                tarefaService.salvarTarefa(tarefa);
//                return new ResponseEntity<>("Tarefa salva com sucesso!", HttpStatus.CREATED);
//            }catch (Exception ex){
//                return new ResponseEntity<>("Erro ao salvar!", HttpStatus.BAD_REQUEST);
//            }
//        }
//        return new ResponseEntity<>("Nao Autorizado", HttpStatus.UNAUTHORIZED);
//
//
//    }
    @CrossOrigin
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarTarefa( @RequestBody Tarefa tarefa) {
        Optional<Usuario> user = usuarioService.getUsuario("vhal9");
        if( true) {
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
    //public ResponseEntity<String> alterarTarefa(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha,@RequestBody Tarefa tarefa){
    public ResponseEntity<String> alterarTarefa(@RequestBody Tarefa tarefa){
        Optional<Usuario> user = usuarioService.getUsuario("vhal9");
        //if( user.isPresent() && user.get().getSenha().equals(senha)) {
        if(true){
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
    //public ResponseEntity<String> excluirTarefa(@RequestHeader(value="login") String login, @RequestHeader(value="senha") String senha,@PathVariable Long id){
    public ResponseEntity<String> excluirTarefa(@PathVariable Long id){
        Optional<Usuario> user = usuarioService.getUsuario("vhal9");
        //if( user.isPresent() && user.get().getSenha().equals(senha)) {
        if(true) {
            if(tarefaService.excluirTarefa(id)){
                return new ResponseEntity<>("Tarefa excluida com sucesso!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Erro ao excluir!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Nao Autorizado", HttpStatus.UNAUTHORIZED);
    }


}
