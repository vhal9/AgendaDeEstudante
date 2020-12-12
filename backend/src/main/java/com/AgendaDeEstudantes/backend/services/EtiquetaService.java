package com.AgendaDeEstudantes.backend.services;

import com.AgendaDeEstudantes.backend.models.Etiqueta;
import com.AgendaDeEstudantes.backend.repositorys.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtiquetaService {
    @Autowired
    EtiquetaRepository etiquetaRepository;

    public List<Etiqueta> listar(){
        return etiquetaRepository.findAll();
    }
    public Etiqueta salvar(Etiqueta etiqueta){
        return etiquetaRepository.save(etiqueta);
    }
    public Etiqueta alterar(Etiqueta etiqueta){
        return etiquetaRepository.save(etiqueta);
    }
    public boolean excluir(Long id){
        int count = (int) etiquetaRepository.count();
        etiquetaRepository.deleteById(id);
        return (count > etiquetaRepository.count());
    }
    public Optional<Etiqueta> getEtiqueta(Long id){
        return etiquetaRepository.findById(id);
    }

}
