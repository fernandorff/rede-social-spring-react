package com.redesocial.__security.controller.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarUsuarioRequest {

    @NotBlank(message = "O nome completo não pode ser vazio")
    private String nomeCompleto;

    @NotBlank(message = "O apelido não pode ser vazio")
    private String apelido;

    private String imagemPerfil;
}

