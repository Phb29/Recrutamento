package paulo.recrutamentointerno.service;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import paulo.recrutamentointerno.domain.model.Usuario;
import paulo.recrutamentointerno.domain.model.UsuarioVerificador;
import paulo.recrutamentointerno.domain.model.Vaga;
import paulo.recrutamentointerno.domain.model.dto.ResponseDTO;
import paulo.recrutamentointerno.domain.model.dto.UsuarioDTO;
import paulo.recrutamentointerno.domain.model.enums.TipoSituacaoUsuario;
import paulo.recrutamentointerno.repository.UsuarioRepository;
import paulo.recrutamentointerno.repository.VagaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private VagaRepository vagaRepository;

    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    public ResponseDTO<UsuarioDTO> carregarPeloLogin(String login) {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.get());

        usuarioDTO.setSenha(null);

        return ResponseDTO.<UsuarioDTO>builder()
                .data(usuarioDTO)
                .message("Cadastrado com sucesso.")
                .description("Faça o login!")
                .status(OK.getStatusCode())
                .build();
    }

    public ResponseDTO<UsuarioDTO> inserir(UsuarioDTO usuario) {

        Usuario usuarioEntity = new Usuario(usuario);
        usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
        try {
            usuarioRepository.save(usuarioEntity);
            return ResponseDTO.<UsuarioDTO>builder()
                    .data(usuario)
                    .message("Cadastrado com sucesso.")
                    .description("Faça o login!")
                    .status(OK.getStatusCode())
                    .build();
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                DataIntegrityViolationException cve = (DataIntegrityViolationException) e;
                ConstraintViolationException cve2 = (ConstraintViolationException) cve.getCause();
                System.out.println("Nome da restrição violada: " + cve2.getConstraintName());
                if (cve2.getConstraintName() != null) {
                    switch (cve2.getConstraintName()) {
                        case "uk_usuario_email":
                            return ResponseDTO.<UsuarioDTO>builder()
                                    .data(usuario)
                                    .message("Erro ao processar a requisição")
                                    .description("E-mail já cadastrado.")
                                    .status(PAYMENT_REQUIRED.getStatusCode())
                                    .build();
                        case "uk_usuario_login":
                            return ResponseDTO.<UsuarioDTO>builder()
                                    .data(usuario)
                                    .message("Erro ao processar a requisição")
                                    .description("Usuário já cadastrado.")
                                    .status(PAYMENT_REQUIRED.getStatusCode())
                                    .build();
                        default:
                            return ResponseDTO.<UsuarioDTO>builder()
                                    .data(usuario)
                                    .message("Erro ao processar a requisição")
                                    .description("Erro na propridede " + cve2.getConstraintName())
                                    .status(PAYMENT_REQUIRED.getStatusCode())
                                    .build();
                    }
                } else
                    return ResponseDTO.<UsuarioDTO>builder().data(usuario).message("Erro ao processar a requisição").status(BAD_REQUEST.getStatusCode()).build();
            } else {
                return ResponseDTO.<UsuarioDTO>builder().data(usuario).message("Erro ao processar a requisição").status(BAD_REQUEST.getStatusCode()).build();
            }
        }

    }

    public UsuarioDTO alterar(UsuarioDTO usuario) {
        Usuario usuarioEntity = new Usuario(usuario);
        usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
    }


    public UsuarioDTO buscarPorId(Long id) {
        return new UsuarioDTO(usuarioRepository.findById(id).get());
    }

    public void excluirVaga(Long id) {
        if (vagaRepository.existsById(id)) {
            vagaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Vaga não encontrada com o ID: " + id);
        }
    }
}