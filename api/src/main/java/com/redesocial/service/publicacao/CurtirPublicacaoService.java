package com.redesocial.service.publicacao;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.service.usuario.BuscarUsuarioService;
import com.redesocial._domain.Curtida;
import com.redesocial._domain.Publicacao;
import com.redesocial._repository.CurtidaRepository;
import com.redesocial._repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurtirPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Transactional
    public void curtir(Long publicacaoId, Long usuarioId) {

        Usuario usuario = buscarUsuarioService.porId(usuarioId);
        Publicacao publicacao = buscarPublicacaoService.porId(publicacaoId);

        if (curtidaRepository.existsByPublicacaoAndUsuario(publicacao, usuario)) {
            curtidaRepository.deleteByPublicacaoAndUsuario(publicacao, usuario);
            return;
        }

        Curtida curtida = new Curtida();

        usuario.adicionarCurtida(curtida);
        publicacao.adicionarCurtida(curtida);
        curtida.adicionarUsuario(usuario);
        curtida.adicionarPublicacao(publicacao);

        usuarioRepository.save(usuario);
        publicacaoRepository.save(publicacao);
        curtidaRepository.save(curtida);
    }
}
