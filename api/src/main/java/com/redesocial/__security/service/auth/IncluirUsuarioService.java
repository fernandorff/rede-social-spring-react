package com.redesocial.__security.service.auth;

import static com.redesocial.__security._domain.enums.Funcao.USUARIO;
import static com.redesocial.__security.mapper.UsuarioMapper.toEntity;
import static com.redesocial.__security.mapper.UsuarioMapper.toResponse;

import com.redesocial.__security._domain.Permissao;
import com.redesocial.__security._domain.Usuario;
import com.redesocial.__security._repository.UsuarioRepository;
import com.redesocial.__security.controller.request.UsuarioRequest;
import com.redesocial.__security.controller.response.UsuarioResponse;
import com.redesocial.__security.service.auth.core.ValidaEmailUnicoService;
import com.redesocial.service._core.NowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NowService nowService;

    @Autowired
    private ValidaEmailUnicoService validaEmailUnicoService;

    @Transactional
    public UsuarioResponse incluir(UsuarioRequest request) {

        validaEmailUnicoService.validar(request.getEmail());

        Usuario usuario = toEntity(request);
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));
        usuario.adicionarPermissao(getPermissaoPadrao());
        usuario.setAtivo(true);
        usuario.setDataCriacao(nowService.getDateTime());
        usuarioRepository.save(usuario);
        return toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }

    private Permissao getPermissaoPadrao() {
        Permissao permissao = new Permissao();
        permissao.setFuncao(USUARIO);
        return permissao;
    }
}
