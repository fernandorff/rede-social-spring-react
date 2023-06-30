package com.redesocial.controller.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioRequest {

    @NotBlank(message = "O texto não pode ser vazio.")
    private String texto;
}

