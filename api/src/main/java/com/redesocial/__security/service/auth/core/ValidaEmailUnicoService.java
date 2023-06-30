package com.redesocial.__security.service.auth.core;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import com.redesocial.__security._repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidaEmailUnicoService {

    @Autowired
    private UsuarioRepository repository;

    public void validar(String email) {

        boolean existeOutroUsuarioComMesmoEmail = repository.existsByEmail(email);

        if (existeOutroUsuarioComMesmoEmail) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Email do usuario deve ser único");
        }
    }
}
