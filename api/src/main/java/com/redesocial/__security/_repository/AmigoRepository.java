package com.redesocial.__security._repository;

import com.redesocial.__security._domain.Amigo;
import com.redesocial.__security._domain.Usuario;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AmigoRepository extends JpaRepository<Amigo, Long> {

    Boolean existsByUsuarioRecebendoAndUsuarioEnviando(Usuario usuario1, Usuario usuario2);

    boolean existsByUsuarioEnviandoAndUsuarioRecebendoAndConfirmadoTrueOrUsuarioEnviandoAndUsuarioRecebendoAndConfirmadoTrue(
        Usuario usuario1, Usuario usuario2, Usuario usuario3, Usuario usuario4);

    boolean existsByConfirmadoFalseAndUsuarioEnviandoAndUsuarioRecebendo(
        Usuario usuario1, Usuario usuario2);

    List<Amigo> findByConfirmadoTrueAndUsuarioEnviandoOrConfirmadoTrueAndUsuarioRecebendo(
        Usuario usuario1, Usuario usuario2);

    List<Amigo> findByConfirmadoFalseAndUsuarioEnviandoOrConfirmadoFalseAndUsuarioRecebendo(
        Usuario usuario1, Usuario usuario2);
}
