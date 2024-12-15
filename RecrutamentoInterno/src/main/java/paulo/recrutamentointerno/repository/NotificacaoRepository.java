package paulo.recrutamentointerno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paulo.recrutamentointerno.domain.model.Notificacao;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByDestinatarioId(Long idUsuario);
}
