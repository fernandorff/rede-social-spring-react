package com.redesocial.__security.service.amigo;

import static com.redesocial.__security.mapper.AmigoMapper.toResponse;

import com.redesocial.__security._domain.Amigo;
import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.AmigoRepository;
import com.redesocial.__security.controller.response.AmigosDoUsuarioResponse;
import com.redesocial.__security.service.usuario.BuscarUsuarioService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListarSolicitacoesEnviadasDoUsuarioService {

    @Autowired
    AmigoRepository amigoRepository;

    @Autowired
    BuscarUsuarioService buscarUsuarioService;

    public List<AmigosDoUsuarioResponse> listar(Long usuarioId) {
        Usuario usuario = buscarUsuarioService.porId(usuarioId);

        List<Amigo> amizadesConfirmadas = amigoRepository.findByConfirmadoFalseAndUsuarioEnviandoOrConfirmadoFalseAndUsuarioRecebendo(
            usuario, usuario);

        return amizadesConfirmadas.stream()
            .filter(amizade -> amizade.getUsuarioEnviando().equals(usuario))
            .map(amizade -> {
                Usuario amigoUsuario = amizade.getUsuarioRecebendo();
                return toResponse(amizade, amigoUsuario);
            })
            .collect(Collectors.toList());
    }
}
