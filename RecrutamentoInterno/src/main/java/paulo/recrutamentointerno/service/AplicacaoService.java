package paulo.recrutamentointerno.service;



import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import paulo.recrutamentointerno.domain.model.Aplicacao;
import paulo.recrutamentointerno.domain.model.Usuario;
import paulo.recrutamentointerno.domain.model.Vaga;
import paulo.recrutamentointerno.domain.model.dataService.impl.AplicacaoInterfaceDAO;
import paulo.recrutamentointerno.domain.model.dto.AplicacaoDTO;
import paulo.recrutamentointerno.domain.model.dto.ResponseDTO;
import paulo.recrutamentointerno.repository.UsuarioRepository;
import paulo.recrutamentointerno.repository.VagaRepository;


@Service
public class AplicacaoService {
    @Autowired
    private AplicacaoInterfaceDAO aplicacaoInterfaceDAO;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VagaRepository vagaRepository;

    public ResponseDTO<AplicacaoDTO> saveAplicacao(AplicacaoDTO aplicacaoDto) {
        if (aplicacaoDto.getId() == null) {
            Aplicacao apply;
            try {

                apply = aplicacaoInterfaceDAO.save(aplicacaoDto.toEntity());
                aplicacaoDto.setId(apply.getId());


                Usuario candidato = usuarioRepository.findById(apply.getIdCandidato().getId())
                        .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

                Vaga vaga = vagaRepository.findById(apply.getIdVaga().getId())
                        .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));


                String mensagem = "O candidato " + candidato.getNome()
                        + " aplicou para a vaga: " + vaga.getTitulo();


                notificacaoService.criarNotificacao(
                        vaga.getId(),
                        candidato.getId(),
                        mensagem
                );

            } catch (Exception e) {
                return tratarExcecoes(e, aplicacaoDto);
            }

            return ResponseDTO.<AplicacaoDTO>builder()
                    .data(aplicacaoDto)
                    .message("Vaga aplicada com sucesso!")
                    .description("")
                    .status(HttpStatus.OK.value())
                    .build();
        } else {
            return ResponseDTO.<AplicacaoDTO>builder()
                    .data(null)
                    .message("Vaga atualizada")
                    .description("A vaga foi atualizada com sucesso")
                    .status(HttpStatus.OK.value())
                    .build();
        }
    }

    private ResponseDTO<AplicacaoDTO> tratarExcecoes(Exception e, AplicacaoDTO aplicacaoDto) {
        if (e instanceof DataIntegrityViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
            if ("uk_aplicacao_vaga_candidato".equals(cve.getConstraintName())) {
                return ResponseDTO.<AplicacaoDTO>builder()
                        .data(aplicacaoDto)
                        .message("Você já se Registrou nesta vaga!")
                        .status(HttpStatus.CONFLICT.value())
                        .build();
            }
        }
        return ResponseDTO.<AplicacaoDTO>builder()
                .data(aplicacaoDto)
                .message("Erro ao processar a requisição")
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }



}