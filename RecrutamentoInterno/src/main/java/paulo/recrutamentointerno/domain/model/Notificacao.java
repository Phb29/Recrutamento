package paulo.recrutamentointerno.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "notificacao", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private Instant dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario_destinatario", nullable = false)
    private Usuario destinatario;

    @ManyToOne
    @JoinColumn(name = "id_usuario_remetente", nullable = true)
    private Usuario remetente;

    @Column(nullable = false)
    private Boolean visualizada = false;

    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = true)
    private Vaga vaga;
}
