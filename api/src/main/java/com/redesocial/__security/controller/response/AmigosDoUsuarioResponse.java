package com.redesocial.__security.controller.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class AmigosDoUsuarioResponse {

    private Long amizadeId;
    private Boolean amizadeConfirmada;
    private Long usuarioId;
    private String usuarioApelido;
    private String usuarioImagemPerfil;

}
