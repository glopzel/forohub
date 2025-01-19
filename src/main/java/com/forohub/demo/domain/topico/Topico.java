package com.forohub.demo.domain.topico;

import com.forohub.demo.domain.respuesta.Respuesta;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String mensaje;
    @Enumerated(EnumType.STRING)
    private Curso curso;
    private LocalDateTime fechaCreacion;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Respuesta> respuestasL = new HashSet<>();
    @Setter
    private Integer respuestas;
    private String status;

    public Topico(DatosAgregarTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.autor = datosRegistroTopico.autor();
        this.mensaje = datosRegistroTopico.mensaje();
        this.curso = datosRegistroTopico.curso();
        this.fechaCreacion = LocalDateTime.now();
        this.respuestas = 0;
        this.status = "ABIERTO";
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<Respuesta> getRespuestasL() {
        return respuestasL;
    }

    public void setRespuestasL(Set<Respuesta> respuestasL) {
        this.respuestasL = respuestasL;
    }

    public Integer getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Integer respuestas) {
        this.respuestas = respuestas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
