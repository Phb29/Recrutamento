package paulo.recrutamentointerno.domain.model.dto;


import org.springframework.beans.BeanUtils;
import paulo.recrutamentointerno.domain.model.Usuario;
import paulo.recrutamentointerno.domain.model.enums.Sexo;
import paulo.recrutamentointerno.domain.model.enums.TipoSituacaoUsuario;
import paulo.recrutamentointerno.domain.model.enums.TipoUsuario;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private Sexo sexo;
    private TipoUsuario tipoUsuario;
    private TipoSituacaoUsuario situacao;

    public UsuarioDTO(Usuario usuario) {
        BeanUtils.copyProperties(usuario, this);
    }

    public UsuarioDTO() {

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public TipoSituacaoUsuario getSituacao() {
        return situacao;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Sexo getSexo() {
        return sexo;
    }
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    public void setSituacao(TipoSituacaoUsuario situacao) {
        this.situacao = situacao;
    }
}
