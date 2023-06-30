package com.redesocial.__security.controller;

import com.redesocial.__security.controller.response.UsuarioResponse;
import com.redesocial.__security.service.auth.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @PostMapping
    public UsuarioResponse login() {
        return usuarioAutenticadoService.getResponse();
    }
}
