package com.redesocial.__security.mapper;

import static java.util.Objects.isNull;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security.controller.request.UsuarioRequest;
import com.redesocial.__security.controller.response.UsuarioResponse;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNomeCompleto(request.getNomeCompleto());
        entity.setApelido(request.getApelido());
        entity.setEmail(request.getEmail());
        entity.setDataNascimento(request.getDataNascimento());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {

        if (isNull(entity)) {
            return UsuarioResponse.builder().build();
        }

        UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getId());
        response.setNomeCompleto(entity.getNomeCompleto());
        response.setApelido(entity.getApelido());
        response.setEmail(entity.getEmail());
        response.setDataNascimento(entity.getDataNascimento());
        response.setImagemPerfil(entity.getImagemPerfil());
        response.setDataCriacao(entity.getDataCriacao());
        response.setAtivo(entity.getAtivo());
        return response;
    }


}
