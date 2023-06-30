package com.redesocial.__security.service.amigo;

import com.redesocial.__security._domain.Amigo;
import com.redesocial.__security._repository.AmigoRepository;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.service.usuario.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AceitarSolicitacaoDeAmizadeService {

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private BuscarAmigoService buscarAmigoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public void aceitar(Long amigoId) {

        Amigo amigo = buscarAmigoService.porId(amigoId);

        amigo.setConfirmado(true);

        amigoRepository.save(amigo);
    }
}
