package paulo.recrutamentointerno.domain.model.dto;


import lombok.Builder;
import lombok.Data;
import paulo.recrutamentointerno.domain.model.Aplicacao;
import paulo.recrutamentointerno.domain.model.Usuario;
import paulo.recrutamentointerno.domain.model.Vaga;

@Data
public class AplicacaoDTO {

    private Long id;
    private Long idVaga;
    private Long idUsuario;


    @Builder
    public AplicacaoDTO(Long id, Long idVaga, Long idUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idVaga = idVaga;
    }

    public Aplicacao toEntity() {
        return Aplicacao.builder()
                .id(this.id)
                .idVaga(Vaga.builder().id(this.idVaga).build())
                .idCandidato(Usuario.builder().id(this.idUsuario).build())
                .build();
    }

//    public Vaga toVagaEntity() {
//        return Vaga.builder()
//                .id(this.id)
//                .titulo(this.titulo)
//                .descricao(this.descricao)
//                .requisitos(this.requisitos)
//                .build();
//    }
}
