package paulo.recrutamentointerno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulo.recrutamentointerno.domain.model.Notificacao;
import paulo.recrutamentointerno.domain.model.Usuario;
import paulo.recrutamentointerno.domain.model.Vaga;
import paulo.recrutamentointerno.repository.NotificacaoRepository;
import paulo.recrutamentointerno.repository.VagaRepository;
import paulo.recrutamentointerno.repository.UsuarioRepository;

import java.time.Instant;
import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criarNotificacao(Long idVaga, Long idRemetente, String mensagem) {
        Vaga vaga = vagaRepository.findById(idVaga)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        Usuario recrutador = vaga.getCreatedBy();

        Usuario remetente = usuarioRepository.findById(idRemetente)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        Notificacao notificacao = Notificacao.builder()
                .mensagem(mensagem)
                .dataCriacao(Instant.now())
                .destinatario(recrutador)
                .remetente(remetente)
                .vaga(vaga)
                .visualizada(false)
                .build();

        notificacaoRepository.save(notificacao);
    }

    public List<Notificacao> buscarNotificacoesPorUsuario(Long idUsuario) {
        return notificacaoRepository.findByDestinatarioId(idUsuario);
    }
}
