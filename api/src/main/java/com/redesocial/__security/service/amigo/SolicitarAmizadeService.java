package com.redesocial.__security.service.amigo;

import com.redesocial.__security._domain.Amigo;
import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.AmigoRepository;
import com.redesocial.__security.service.usuario.BuscarUsuarioService;
import com.redesocial.__security.validator.SolicitarAmizadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitarAmizadeService {

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private SolicitarAmizadeValidator solicitarAmizadeValidator;

    public void solicitarAmizade(Long usuarioEnviandoId, Long usuarioRecebendoId) {

        Usuario usuarioEnviando = buscarUsuarioService.porId(usuarioEnviandoId);
        Usuario usuarioRecebendo = buscarUsuarioService.porId(usuarioRecebendoId);

        solicitarAmizadeValidator.validar(usuarioEnviando, usuarioRecebendo);

        Amigo amigo = Amigo.builder()
            .usuarioEnviando(usuarioEnviando)
            .usuarioRecebendo(usuarioRecebendo)
            .confirmado(false)
            .build();

        amigoRepository.save(amigo);
    }
}
