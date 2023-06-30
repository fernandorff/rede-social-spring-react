package com.redesocial.controller.request;

import com.redesocial._domain.enums.Visibilidade;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacaoRequest {

    @NotBlank(message = "O titulo não pode ser vazio")
    private String titulo;

    @NotBlank(message = "O texto não pode ser vazio")
    private String texto;

    private String imagem;

    private Visibilidade visibilidade;
}

