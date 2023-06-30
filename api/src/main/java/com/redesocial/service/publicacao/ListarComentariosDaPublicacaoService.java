package com.redesocial.service.publicacao;

import com.redesocial._repository.ComentarioRepository;
import com.redesocial._repository.PublicacaoRepository;
import com.redesocial.controller.response.ComentarioResponse;
import com.redesocial.mapper.ComentarioMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListarComentariosDaPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    public List<ComentarioResponse> listarComentariosDaPublicacao(Long publicacaoId) {

        return buscarPublicacaoService.porId(publicacaoId).getComentarios().stream()
            .map(ComentarioMapper::toResponse).collect(Collectors.toList());
    }
}
