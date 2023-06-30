package com.redesocial.service.publicacao;

import static com.redesocial.mapper.ComentarioMapper.toEntity;
import static com.redesocial.mapper.ComentarioMapper.toResponse;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.service.usuario.BuscarUsuarioService;
import com.redesocial._domain.Comentario;
import com.redesocial._domain.Publicacao;
import com.redesocial._repository.ComentarioRepository;
import com.redesocial._repository.PublicacaoRepository;
import com.redesocial.controller.request.ComentarioRequest;
import com.redesocial.controller.response.ComentarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Transactional
    public ComentarioResponse comentar(Long publicacaoId, Long usuarioId,
        ComentarioRequest request) {

        Usuario usuario = buscarUsuarioService.porId(usuarioId);
        Publicacao publicacao = buscarPublicacaoService.porId(publicacaoId);
        Comentario comentario = toEntity(request);

        usuario.adicionarComentario(comentario);
        publicacao.adicionarComentario(comentario);
        comentario.adicionarUsuario(usuario);
        comentario.adicionarPublicacao(publicacao);

        usuarioRepository.save(usuario);
        publicacaoRepository.save(publicacao);
        comentarioRepository.save(comentario);

        return toResponse(comentario);
    }
}
