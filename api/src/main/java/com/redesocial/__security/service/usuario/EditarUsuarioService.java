package com.redesocial.__security.service.usuario;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.controller.request.EditarUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditarUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BuscarUsuarioService buscarUsuarioService;

    public void editarUsuario(Long usuarioId, EditarUsuarioRequest request) {

        Usuario usuario = buscarUsuarioService.porId(usuarioId);

        usuario.setApelido(request.getApelido());
        usuario.setNomeCompleto(request.getNomeCompleto());
        usuario.setImagemPerfil(request.getImagemPerfil());

        usuarioRepository.save(usuario);
    }

}
