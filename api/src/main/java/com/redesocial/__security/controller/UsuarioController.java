package com.redesocial.__security.controller;

import com.redesocial.__security.controller.request.EditarUsuarioRequest;
import com.redesocial.__security.controller.request.UsuarioRequest;
import com.redesocial.__security.controller.response.UsuarioResponse;
import com.redesocial.__security.service.usuario.AtivarUsuarioService;
import com.redesocial.__security.service.usuario.DesativarUsuarioService;
import com.redesocial.__security.service.usuario.EditarUsuarioService;
import com.redesocial.__security.service.auth.IncluirUsuarioService;
import com.redesocial.__security.service.usuario.BuscarUsuarioService;
import com.redesocial.__security.service.usuario.DetalharUsuarioService;
import com.redesocial.__security.service.usuario.ListarPublicacoesDoUsuarioService;
import com.redesocial.__security.service.usuario.ListarUsuariosService;
import com.redesocial.__security.service.usuario.ProcurarUsuariosPorNomeOuEmailService;
import com.redesocial.controller.response.PublicacaoResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private ListarUsuariosService listarUsuariosService;

    @Autowired
    private DesativarUsuarioService desativarUsuarioService;

    @Autowired
    private AtivarUsuarioService ativarUsuarioService;

    @Autowired
    private DetalharUsuarioService detalharUsuarioService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private ProcurarUsuariosPorNomeOuEmailService procurarUsuariosPorNomeOuEmailService;

    @Autowired
    private ListarPublicacoesDoUsuarioService listarPublicacoesDoUsuarioService;

    @Autowired
    private EditarUsuarioService editarUsuarioService;

    @PostMapping
    public UsuarioResponse cadastrar(@Valid @RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return listarUsuariosService.listar();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{usuarioId}/desativar")
    public void desativar(@PathVariable Long usuarioId) {
        desativarUsuarioService.desativar(usuarioId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{usuarioId}/ativar")
    public void ativar(@PathVariable Long usuarioId) {
        ativarUsuarioService.ativar(usuarioId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{usuarioId}")
    public UsuarioResponse detalhar(@PathVariable Long usuarioId) {
        return detalharUsuarioService.detalhar(usuarioId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("procurar")
    public List<UsuarioResponse> procurarPorNomeCompletoOuEmail(
        @RequestParam("query") String query) {
        return procurarUsuariosPorNomeOuEmailService
            .procurarPorNomeOuEmail(query.toLowerCase());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("{usuarioId}/publicacoes/{usuarioAcessandoId}")
    public Page<PublicacaoResponse> listarPublicacoesDoUsuario(
        @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable pageable,
        @PathVariable Long usuarioId, @PathVariable Long usuarioAcessandoId) {
        return listarPublicacoesDoUsuarioService.listarPublicacoesDoUsuario(pageable, usuarioId,
            usuarioAcessandoId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{usuarioId}")
    public void editarUsuario(@PathVariable Long usuarioId,
        @Valid @RequestBody EditarUsuarioRequest request) {
        editarUsuarioService.editarUsuario(usuarioId, request);
    }
}
