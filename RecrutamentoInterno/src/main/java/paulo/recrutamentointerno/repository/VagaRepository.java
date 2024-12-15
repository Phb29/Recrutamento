package paulo.recrutamentointerno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paulo.recrutamentointerno.domain.model.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
}