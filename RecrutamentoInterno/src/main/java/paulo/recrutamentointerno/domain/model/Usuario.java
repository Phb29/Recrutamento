package paulo.recrutamentointerno.domain.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import paulo.recrutamentointerno.domain.model.dto.UsuarioDTO;
import paulo.recrutamentointerno.domain.model.enums.Sexo;
import paulo.recrutamentointerno.domain.model.enums.TipoSituacaoUsuario;
import paulo.recrutamentointerno.domain.model.enums.TipoUsuario;

import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "uk_usuario_login", columnNames = "login"),
        @UniqueConstraint(name = "uk_usuario_email", columnNames = "email")
})
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TipoSituacaoUsuario situacao;

    public Usuario(UsuarioDTO usuario) {
        BeanUtils.copyProperties(usuario, this);
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    @Builder
    public Usuario(Long id, String nome, String login, String senha, String email, Sexo sexo, TipoUsuario tipoUsuario, TipoSituacaoUsuario situacao) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.sexo = sexo;
        this.tipoUsuario = tipoUsuario;
        this.situacao = situacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(id, other.id);
    }
}
