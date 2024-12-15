package paulo.recrutamentointerno.domain.model.dataService.impl;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paulo.recrutamentointerno.domain.model.Aplicacao;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AplicacaoInterfaceDAO extends JpaRepository<Aplicacao, Long> {

    List<Aplicacao> findAll();

    Optional<Aplicacao> findById(Long id);

    Aplicacao save(Aplicacao aplicacao);

    void delete(Aplicacao aplicacao);
}
