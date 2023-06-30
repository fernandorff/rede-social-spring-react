package com.redesocial.__security.controller.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
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
public class UsuarioResponse {

    private Long id;
    private String nomeCompleto;
    private String apelido;
    private String email;
    private LocalDate dataNascimento;
    private String imagemPerfil;
    private LocalDateTime dataCriacao;
    private Boolean ativo;
}
