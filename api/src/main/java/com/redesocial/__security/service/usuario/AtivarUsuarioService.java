package com.redesocial.__security.service.usuario;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtivarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Transactional
    public void ativar(Long usuarioId) {

        Usuario usuario = buscarUsuarioService.porId(usuarioId);

        usuario.setAtivo(true);

        usuarioRepository.save(usuario);
    }
}
