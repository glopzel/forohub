package com.forohub.demo.controller;

import com.forohub.demo.domain.topico.DatosActualizarTopico;
import com.forohub.demo.domain.topico.DatosAgregarTopico;
import com.forohub.demo.domain.topico.DatosTopico;
import com.forohub.demo.domain.topico.TopicRepository;
import com.forohub.demo.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicoService;

    @PostMapping
    public ResponseEntity<DatosTopico> registrarTopico(@RequestBody @Valid DatosAgregarTopico datosAgregarTopic, UriComponentsBuilder uriComponentsBuilder) {
        var response = topicoService.registrarTopico(datosAgregarTopic);
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosTopico>> listarTopicos(@PageableDefault(size = 3, sort = {"fechaCreacion"}) Pageable paginacion) {
        var page = topicRepository.findAll(paginacion).map(DatosTopico::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopic(@PathVariable Long id) {
        topicoService.eliminarTopic(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosTopico> actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datosActualizarTopico) {
        var response = topicoService.actualizarTopic(id, datosActualizarTopico);
        return ResponseEntity.ok(response);
    }

}
