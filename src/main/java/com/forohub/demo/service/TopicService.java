package com.forohub.demo.service;

import com.forohub.demo.domain.topico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public DatosTopico registrarTopico(DatosAgregarTopico datosAgregarTopic) {
        // Valida si el topico ya existe
        if (topicRepository.existsByTitulo(datosAgregarTopic.titulo()) && topicRepository.existsByMensaje(datosAgregarTopic.mensaje())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Éste tópico ya existe");
        }
        var Topic = new Topico(datosAgregarTopic);
        topicRepository.save(Topic);

        return new DatosTopico(Topic);
    }

    public void eliminarTopic(Long id) {
        var topico = TopicExists(id);
        topicRepository.delete(topico);
    }

    public Topico TopicExists(Long id) {
        var topicoOptional = topicRepository.findById(id);
        if (topicoOptional.isPresent()) {
            return topicoOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Éste tópico no existe");
        }
    }

    public DatosTopico actualizarTopic(Long id, DatosActualizarTopico datosActualizarTopic) {
        var topico = TopicExists(id);
        topico.setTitulo(datosActualizarTopic.titulo());
        topico.setAutor(datosActualizarTopic.autor());
        topico.setMensaje(datosActualizarTopic.mensaje());
        topico.setCurso(datosActualizarTopic.curso());
        if(datosActualizarTopic.status().toUpperCase().equals("CERRADO")){
            topico.setStatus("CERRADO");
        }
        topicRepository.save(topico);

        return new DatosTopic(topico);
    }
}
