package paulo.recrutamentointerno.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulo.recrutamentointerno.domain.model.Notificacao;
import paulo.recrutamentointerno.service.NotificacaoService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<Notificacao>> listarNotificacoes(@PathVariable Long idUsuario) {
        List<Notificacao> notificacoes = notificacaoService.buscarNotificacoesPorUsuario(idUsuario);
        return ResponseEntity.ok(notificacoes);
    }
}
