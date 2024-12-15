package paulo.recrutamentointerno.service;



import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import paulo.recrutamentointerno.domain.model.dto.AcessDTO;
import paulo.recrutamentointerno.domain.model.dto.AuthenticationDTO;
import paulo.recrutamentointerno.security.jwt.JwtUtils;


@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public AcessDTO login(AuthenticationDTO authenticationDTO) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getSenha());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.gerarTokenFromUserDetailsImpl(userDetails);


            return new AcessDTO(token, userDetails.getId());
        } catch (Exception e) {
            return new AcessDTO("Acesso negado");
        }
    }
}
