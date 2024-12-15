package paulo.recrutamentointerno.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import paulo.recrutamentointerno.domain.model.Usuario;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);
}