package paulo.recrutamentointerno.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import paulo.recrutamentointerno.domain.model.dto.AplicacaoDTO;


@Entity
@Table(name = "aplicacao", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "uk_aplicacao_vaga_candidato", columnNames = {"idVaga", "idCandidato"})})
@Data
public class Aplicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idVaga", referencedColumnName = "id", nullable = false)
    private Vaga idVaga;

    @ManyToOne
    @JoinColumn(name = "idCandidato", referencedColumnName = "id", nullable = false)
    private Usuario idCandidato;


    public Aplicacao() {
    }

    @Builder
    public Aplicacao(Long id, Vaga idVaga, Usuario idCandidato) {
        this.id = id;
        this.idVaga = idVaga;
        this.idCandidato = idCandidato;
    }


    public Aplicacao(AplicacaoDTO aplicacaoDto) {
        this.id = aplicacaoDto.getId();
        this.idVaga = Vaga.builder().id(aplicacaoDto.getIdVaga()).build();
        this.idCandidato = Usuario.builder().id(aplicacaoDto.getIdUsuario()).build();
    }

}
