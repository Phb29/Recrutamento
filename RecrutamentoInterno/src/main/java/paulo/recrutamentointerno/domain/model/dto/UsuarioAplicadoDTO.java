package paulo.recrutamentointerno.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioAplicadoDTO {

    private Long idVaga;
    private String tituloVaga;
    private String descricaoVaga;
    private String requisitosVaga;
    private Long idCandidato;
    private String nomeCandidato;
    private String emailCandidato;
    private String sexoCandidato;


//    private Long id;
//    private String nome;
//    private String login;
//    private String senha;
//    private String email;
//    private Sexo sexo;
//    private TipoUsuario tipoUsuario;
//    private TipoSituacaoUsuario situacao;
//
//
//    private Long id;
//    private String titulo;
//    private String descricao;
//    private String requisitos;


}
