package com.redesocial.__security.validator;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.AmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class SolicitarAmizadeValidator {

    @Autowired
    AmigoRepository amigoRepository;

    public void validar(Usuario usuarioEnviando, Usuario usuarioRecebendo) {

        if (usuarioEnviando.equals(usuarioRecebendo)) {
            throw new ResponseStatusException(BAD_REQUEST,
                "Usuário enviando e recebendo devem ser diferentes.");
        }

        boolean checkAmizadeExiste = amigoRepository.existsByUsuarioEnviandoAndUsuarioRecebendoAndConfirmadoTrueOrUsuarioEnviandoAndUsuarioRecebendoAndConfirmadoTrue(
            usuarioRecebendo, usuarioEnviando, usuarioEnviando, usuarioRecebendo);

        if (checkAmizadeExiste) {
            throw new ResponseStatusException(BAD_REQUEST,
                "Usuários já são amigos.");
        }

        boolean checkSoliticacaoInversa = amigoRepository.existsByConfirmadoFalseAndUsuarioEnviandoAndUsuarioRecebendo(
            usuarioRecebendo, usuarioEnviando);

        if (checkSoliticacaoInversa) {
            throw new ResponseStatusException(BAD_REQUEST,
                "Usuário deve confirmar solicitação ou recusar a solicitação deste usuário.");
        }
    }
}
