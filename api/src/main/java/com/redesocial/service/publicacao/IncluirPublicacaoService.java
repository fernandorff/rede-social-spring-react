package com.redesocial.service.publicacao;

import static com.redesocial.mapper.PublicacaoMapper.toEntity;
import static com.redesocial.mapper.PublicacaoMapper.toResponse;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.service.usuario.BuscarUsuarioService;
import com.redesocial._domain.Publicacao;
import com.redesocial._repository.PublicacaoRepository;
import com.redesocial.controller.request.PublicacaoRequest;
import com.redesocial.controller.response.PublicacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IncluirPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Transactional
    public PublicacaoResponse publicar(Long usuarioId, PublicacaoRequest request) {

        Usuario usuario = buscarUsuarioService.porId(usuarioId);

        Publicacao publicacao = toEntity(request);

        usuario.adicionarPublicacao(publicacao);

        publicacao.adicionarUsuario(usuario);

        usuarioRepository.save(usuario);

        publicacaoRepository.save(publicacao);

        return toResponse(publicacao);
    }
}
