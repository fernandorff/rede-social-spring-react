package com.redesocial.__security._domain;

import com.redesocial._domain.Comentario;
import com.redesocial._domain.Curtida;
import com.redesocial._domain.Publicacao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String email;

    private String apelido;

    private LocalDate dataNascimento;

    private String senha;

    private String imagemPerfil;

    private Boolean ativo;

    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "usuario")
    private List<Publicacao> publicacoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "amigo",
        joinColumns = @JoinColumn(name = "usuario_enviando"),
        inverseJoinColumns = @JoinColumn(name = "usuario_recebendo"))
    private List<Usuario> amigos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Curtida> curtidas = new ArrayList<>();

    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);
    }

    public void adicionarPublicacao(Publicacao publicacao) {
        publicacoes.add(publicacao);
    }

    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public void adicionarCurtida(Curtida curtida) {
        curtidas.add(curtida);
    }
}
