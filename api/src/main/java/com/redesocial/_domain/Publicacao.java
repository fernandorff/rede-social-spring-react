package com.redesocial._domain;

import com.redesocial.__security._domain.Usuario;
import com.redesocial._domain.enums.Visibilidade;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCriacao;

    private String titulo;

    private String texto;

    private String imagem;

    @Enumerated(EnumType.STRING)
    private Visibilidade visibilidade;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "publicacao")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "publicacao")
    private List<Curtida> curtidas = new ArrayList<>();

    public void adicionarUsuario(Usuario usuario) {
        this.setUsuario(usuario);
    }

    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public void adicionarCurtida(Curtida curtida) {
        curtidas.add(curtida);
    }
}
