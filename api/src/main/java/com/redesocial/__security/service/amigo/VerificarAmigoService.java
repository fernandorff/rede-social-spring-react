package com.redesocial.__security.service.amigo;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.AmigoRepository;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.service.usuario.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificarAmigoService {

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;


    public boolean verificarAmigo(Usuario usuario1, Usuario usuario2) {

        return amigoRepository.existsByUsuarioEnviandoAndUsuarioRecebendoAndConfirmadoTrueOrUsuarioEnviandoAndUsuarioRecebendoAndConfirmadoTrue(
            usuario1, usuario2, usuario2, usuario1);
    }
}
