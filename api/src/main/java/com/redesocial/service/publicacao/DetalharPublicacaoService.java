package com.redesocial.service.publicacao;

import com.redesocial._repository.PublicacaoRepository;
import com.redesocial.controller.response.PublicacaoResponse;
import com.redesocial.mapper.PublicacaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalharPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    public PublicacaoResponse detalhar(Long publicacaoId) {

        return PublicacaoMapper.toResponse(buscarPublicacaoService.porId(publicacaoId));
    }
}
