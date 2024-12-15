package paulo.recrutamentointerno.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Data;
import paulo.recrutamentointerno.domain.model.dto.VagaDTO;


@Entity
@Transactional
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "vaga", schema = "public")
@Data
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false,length = 5000)
    private String titulo;

    @Column(nullable = false, length = 5000)
    private String descricao;

    @Column(length = 6000)
    private String requisitos;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Usuario createdBy;

    @Builder
    public Vaga(Long id, String titulo, String descricao, String requisitos, Usuario createdBy) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.requisitos = requisitos;
        this.createdBy = createdBy;
    }

    public Vaga() {
    }


}