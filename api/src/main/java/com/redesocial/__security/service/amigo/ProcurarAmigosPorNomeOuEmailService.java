package com.redesocial.__security.service.amigo;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.AmigoRepository;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.controller.response.UsuarioResponse;
import com.redesocial.__security.mapper.UsuarioMapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcurarAmigosPorNomeOuEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private ListarAmigosDoUsuarioService listarAmigosDoUsuarioService;

    public List<UsuarioResponse> procurarAmigoPorNomeOuEmail
        (Long usuarioId, String query) {

        List<Usuario> listaDeAmigos = listarAmigosDoUsuarioService.retornarObjetos(usuarioId);

        return listaDeAmigos.stream()
            .filter(usuario -> {
                String nomeCompleto = usuario.getNomeCompleto().toLowerCase();
                String email = usuario.getEmail().toLowerCase();
                return nomeCompleto.contains(query) || email.contains(query);
            })
            .map(UsuarioMapper::toResponse)
            .collect(Collectors.toList());
    }
}
