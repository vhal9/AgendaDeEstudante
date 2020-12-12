package com.AgendaDeEstudantes.backend.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tb_tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    private String descricao;

    private Date data;

    @ManyToOne
    @JoinColumn(name="id_etiqueta", referencedColumnName = "id", nullable = true)
    private Etiqueta etiqueta;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    public Tarefa() {

    }

    public Tarefa(Long id, String titulo, String descricao, Date data, Etiqueta etiqueta, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.etiqueta = etiqueta;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
