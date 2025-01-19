package com.forohub.demo.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosAgregarTopico(
        @NotNull
        String titulo,
        @NotNull
        String autor,
        @NotNull
        String mensaje,
        @NotNull
        Curso curso
) {
}
