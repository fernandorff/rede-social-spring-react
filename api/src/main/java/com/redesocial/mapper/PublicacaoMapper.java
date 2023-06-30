package com.redesocial.mapper;

import static java.util.Objects.isNull;

import com.redesocial._domain.Publicacao;
import com.redesocial.controller.request.PublicacaoRequest;
import com.redesocial.controller.response.PublicacaoResponse;
import java.time.LocalDateTime;

public class PublicacaoMapper {

    public static Publicacao toEntity(PublicacaoRequest request) {
        Publicacao entity = new Publicacao();
        entity.setTitulo(request.getTitulo());
        entity.setTexto(request.getTexto());
        entity.setImagem(request.getImagem());
        entity.setVisibilidade(request.getVisibilidade());
        entity.setDataCriacao(LocalDateTime.now());
        return entity;
    }

    public static PublicacaoResponse toResponse(Publicacao entity) {

        if (isNull(entity)) {
            return PublicacaoResponse.builder().build();
        }

        PublicacaoResponse response = new PublicacaoResponse();
        response.setId(entity.getId());
        response.setTitulo(entity.getTitulo());
        response.setTexto(entity.getTexto());
        response.setImagem(entity.getImagem());
        response.setVisibilidade(entity.getVisibilidade());
        response.setDataCriacao(entity.getDataCriacao());
        response.setUsuarioId(entity.getUsuario().getId());
        response.setUsuarioId(entity.getUsuario().getId());
        response.setUsuarioApelido(entity.getUsuario().getApelido());
        response.setUsuarioImagemPerfil(entity.getUsuario().getImagemPerfil());
        response.setQuantidadeLikes(entity.getCurtidas().size());
        response.setQuantidadeComentarios(entity.getComentarios().size());
        return response;
    }
}
