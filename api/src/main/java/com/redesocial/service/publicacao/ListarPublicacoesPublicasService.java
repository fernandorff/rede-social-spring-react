package com.redesocial.service.publicacao;

import com.redesocial._domain.enums.Visibilidade;
import com.redesocial._repository.PublicacaoRepository;
import com.redesocial.controller.response.PublicacaoResponse;
import com.redesocial.mapper.PublicacaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarPublicacoesPublicasService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public Page<PublicacaoResponse> listarPublicacoesPublicas(Pageable pageable) {
        return publicacaoRepository.findByVisibilidade(Visibilidade.PUBLICO, pageable)
            .map(PublicacaoMapper::toResponse);
    }
}
