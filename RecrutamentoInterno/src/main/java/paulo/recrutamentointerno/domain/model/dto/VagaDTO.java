package paulo.recrutamentointerno.domain.model.dto;

import lombok.Builder;
import lombok.Data;
import paulo.recrutamentointerno.domain.model.Vaga;

@Data
public class VagaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String requisitos;
    private Long idRecrutador;

    @Builder
    public VagaDTO(Long id, String titulo, String descricao, String requisitos, Long idRecrutador) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.requisitos = requisitos;
        this.idRecrutador = idRecrutador;
    }

    public Vaga toVagaEntity() {
        Vaga vaga = Vaga.builder()
                .id(this.id)
                .titulo(this.titulo)
                .descricao(this.descricao)
                .requisitos(this.requisitos)
                .build();

        return vaga;
    }
}
