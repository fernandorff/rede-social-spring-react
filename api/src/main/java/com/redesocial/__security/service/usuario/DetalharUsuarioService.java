package com.redesocial.__security.service.usuario;

import com.redesocial.__security.controller.response.UsuarioResponse;
import com.redesocial.__security.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalharUsuarioService {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public UsuarioResponse detalhar(Long usuarioId) {

        return UsuarioMapper.toResponse(buscarUsuarioService.porId(usuarioId));
    }
}
