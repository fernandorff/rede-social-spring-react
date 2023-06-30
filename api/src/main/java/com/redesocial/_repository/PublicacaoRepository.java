package com.redesocial._repository;

import com.redesocial.__security._domain.Usuario;
import com.redesocial._domain.Publicacao;
import com.redesocial._domain.enums.Visibilidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    Page<Publicacao> findByVisibilidade(Visibilidade visibilidade, Pageable pageable);

    Page<Publicacao> findByUsuarioAndVisibilidade(Usuario usuario, Visibilidade visibilidade,
                                                  Pageable pageable);

    Page<Publicacao> findByUsuario(Usuario usuario, Pageable pageable);

}
