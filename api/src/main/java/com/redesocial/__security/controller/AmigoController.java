package com.redesocial.__security.controller;

import com.redesocial.__security.controller.response.AmigosDoUsuarioResponse;
import com.redesocial.__security.controller.response.UsuarioResponse;
import com.redesocial.__security.service.amigo.AceitarSolicitacaoDeAmizadeService;
import com.redesocial.__security.service.amigo.DesfazerAmizadeService;
import com.redesocial.__security.service.amigo.ListarAmigosDoUsuarioService;
import com.redesocial.__security.service.amigo.ListarSolicitacoesEnviadasDoUsuarioService;
import com.redesocial.__security.service.amigo.ListarSolicitacoesRecebidasDoUsuarioService;
import com.redesocial.__security.service.amigo.ProcurarAmigosPorNomeOuEmailService;
import com.redesocial.__security.service.amigo.SolicitarAmizadeService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amigos")
public class AmigoController {

    @Autowired
    private SolicitarAmizadeService solicitarAmizadeService;

    @Autowired
    private AceitarSolicitacaoDeAmizadeService aceitarSolicitacaoDeAmizade;

    @Autowired
    private DesfazerAmizadeService desfazerAmizadeService;

    @Autowired
    private ListarAmigosDoUsuarioService listarAmigosDoUsuarioService;

    @Autowired
    private ListarSolicitacoesRecebidasDoUsuarioService listarSolicitacoesRecebidasDoUsuarioService;

    @Autowired
    private ListarSolicitacoesEnviadasDoUsuarioService listarSolicitacoesEnviadasDoUsuarioService;

    @Autowired
    private ProcurarAmigosPorNomeOuEmailService procurarAmigosPorNomeOuEmailService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{usuarioId}")
    public List<AmigosDoUsuarioResponse> listarAmigosDoUsuario(@PathVariable Long usuarioId) {

        return listarAmigosDoUsuarioService.listarResponse(usuarioId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{usuarioId}/recebidas")
    public List<AmigosDoUsuarioResponse> listarSolicitacoesRecebidasDoUsuario(
        @PathVariable Long usuarioId) {

        return listarSolicitacoesRecebidasDoUsuarioService.listar(usuarioId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{usuarioId}/enviadas")
    public List<AmigosDoUsuarioResponse> listarsolicitacoesEnviadasDoUsuario(
        @PathVariable Long usuarioId) {

        return listarSolicitacoesEnviadasDoUsuarioService.listar(usuarioId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{usuarioEnviandoId}/solicitar/{usuarioRecebendoId}")
    public void enviarSolicitacaoDeAmizade(@PathVariable Long usuarioEnviandoId,
        @PathVariable Long usuarioRecebendoId) {

        solicitarAmizadeService.solicitarAmizade(usuarioEnviandoId, usuarioRecebendoId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{amigoId}/aceitar")
    public void aceitarSolicitacaoDeAmizade(@PathVariable Long amigoId) {

        aceitarSolicitacaoDeAmizade.aceitar(amigoId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{amigoId}/desfazer")
    public void desfazerAmizade(@PathVariable Long amigoId) {

        desfazerAmizadeService.desfazer(amigoId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{usuarioId}/procurar")
    public List<UsuarioResponse> procurarAmigosPorNomeOuEmail(@PathVariable Long usuarioId,
                                                              @RequestParam("query") String query) {

        return procurarAmigosPorNomeOuEmailService.procurarAmigoPorNomeOuEmail(usuarioId,
            query.toLowerCase());
    }


}
