package paulo.recrutamentointerno.domain.model.dataService.impl;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paulo.recrutamentointerno.domain.model.Vaga;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface VagaInterfaceDAO extends JpaRepository<Vaga, Long> {

    List<Vaga> findAll();

    Optional<Vaga> findById(Long id);

    Vaga save(Vaga Vaga);

    void delete(Vaga Vaga);
}
