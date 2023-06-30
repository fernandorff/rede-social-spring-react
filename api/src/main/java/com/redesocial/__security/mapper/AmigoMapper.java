package com.redesocial.__security.mapper;

import static java.util.Objects.isNull;

import com.redesocial.__security._domain.Amigo;
import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security.controller.response.AmigosDoUsuarioResponse;

public class AmigoMapper {

    public static Amigo toEntity(Usuario usuarioEnviando, Usuario usuarioRecebendo) {
        Amigo entity = new Amigo();
        entity.setUsuarioEnviando(usuarioEnviando);
        entity.setUsuarioRecebendo(usuarioRecebendo);
        return entity;
    }

    public static AmigosDoUsuarioResponse toResponse(Amigo amigo, Usuario usuario) {

        if (isNull(amigo)) {
            return AmigosDoUsuarioResponse.builder().build();
        }

        AmigosDoUsuarioResponse response = new AmigosDoUsuarioResponse();
        response.setAmizadeId(amigo.getId());
        response.setAmizadeConfirmada(amigo.getConfirmado());
        response.setUsuarioId(usuario.getId());
        response.setUsuarioApelido(usuario.getApelido());
        response.setUsuarioImagemPerfil(usuario.getImagemPerfil());
        return response;
    }
}
