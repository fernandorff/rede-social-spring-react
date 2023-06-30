package com.redesocial.__security.service.usuario;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario porId(Long usuarioId) {

        return usuarioRepository.findById(usuarioId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
}
