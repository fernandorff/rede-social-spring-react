package com.redesocial.controller.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(NON_NULL)
public class ComentarioResponse {

    private Long id;
    private String texto;
    private LocalDateTime dataCriacao;
    private Long usuarioId;
    private String usuarioApelido;
    private String usuarioImagemPerfil;

}
