package com.redesocial.__security.service.auth;

import static com.redesocial.__security.mapper.UsuarioMapper.toResponse;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.redesocial.__security.__config.UsuarioSecurity;
import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.controller.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticadoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Long getId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UsuarioSecurity) {
            return ((UsuarioSecurity) authentication.getPrincipal()).getId();
        }

        return null;
    }

    public Usuario get() {
        Long id = getId();

        if (isNull(id)) {
            return null;
        }

        return usuarioRepository.findById(getId()).orElse(null);
    }

    public UsuarioResponse getResponse() {
        Usuario entity = get();
        return nonNull(entity) ? toResponse(entity) : new UsuarioResponse();
    }
}
