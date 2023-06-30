package com.redesocial.__security.service.amigo;

import com.redesocial.__security._domain.Amigo;
import com.redesocial.__security._repository.AmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuscarAmigoService {

    @Autowired
    private AmigoRepository amigoRepository;

    public Amigo porId(Long amigoId) {

        return amigoRepository.findById(amigoId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Relacionamento não encontrado."));
    }
}
