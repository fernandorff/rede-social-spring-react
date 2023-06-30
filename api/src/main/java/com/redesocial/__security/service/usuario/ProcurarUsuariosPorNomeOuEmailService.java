package com.redesocial.__security.service.usuario;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.controller.response.UsuarioResponse;
import com.redesocial.__security.mapper.UsuarioMapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcurarUsuariosPorNomeOuEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponse> procurarPorNomeOuEmail(String query) {

        List<Usuario> usuariosEncontrados = usuarioRepository.findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCase(
            query, query);

        return usuariosEncontrados.stream().map(UsuarioMapper::toResponse)
            .collect(Collectors.toList());
    }
}
