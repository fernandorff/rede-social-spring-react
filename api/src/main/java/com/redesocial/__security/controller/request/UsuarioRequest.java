package com.redesocial.__security.controller.request;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank(message = "O nome completo não pode ser vazio")
    private String nomeCompleto;

    @NotBlank(message = "O apelido não pode ser vazio")
    private String apelido;

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email não pode ser vazio")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia")
    private String senha;

    @NotNull(message = "A data de nascimento não pode ser nula")
    private LocalDate dataNascimento;
}

