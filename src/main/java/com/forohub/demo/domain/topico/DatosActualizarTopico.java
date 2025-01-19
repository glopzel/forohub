package com.forohub.demo.domain.topico;

public record DatosActualizarTopico(
        String titulo,
        String autor,
        String mensaje,
        Curso curso,
        String status
) {
}
