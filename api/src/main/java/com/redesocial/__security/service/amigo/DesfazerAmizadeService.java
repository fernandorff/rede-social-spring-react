package com.redesocial.__security.service.amigo;

import com.redesocial.__security._domain.Amigo;
import com.redesocial.__security._repository.AmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesfazerAmizadeService {

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private BuscarAmigoService buscarAmigoService;

    public void desfazer(Long amigoId) {

        Amigo amigo = buscarAmigoService.porId(amigoId);

        amigoRepository.delete(amigo);
    }
}
