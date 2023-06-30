package com.redesocial.__security._repository;

import com.redesocial.__security._domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCase(String query,
        String query1);

    boolean existsByEmail(String email);

}
