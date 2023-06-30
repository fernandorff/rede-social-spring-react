package com.redesocial._domain;

import com.redesocial.__security._domain.Usuario;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCriacao;

    private String texto;

    @ManyToOne
    @JoinColumn(name = "id_publicacao")
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public void adicionarPublicacao(Publicacao publicacao) {
        this.setPublicacao(publicacao);
    }

    public void adicionarUsuario(Usuario usuario) {
        this.setUsuario(usuario);
    }

}
