package paulo.recrutamentointerno.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paulo.recrutamentointerno.domain.model.dto.ResponseDTO;
import paulo.recrutamentointerno.domain.model.dto.UsuarioDTO;
import paulo.recrutamentointerno.service.UsuarioService;

import java.util.List;


@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping(value = "/{login}")
    public ResponseDTO<UsuarioDTO> listarTodos(@PathVariable("login") String login) {
        return usuarioService.carregarPeloLogin(login);
    }

    @PostMapping
    public ResponseDTO<UsuarioDTO> inserir(@RequestBody UsuarioDTO usuario) {
        return usuarioService.inserir(usuario);
    }

    @PutMapping
    public UsuarioDTO alterar(@RequestBody UsuarioDTO usuario) {
        return usuarioService.alterar(usuario);
    }

    @DeleteMapping(value = "/vaga/{id}")
    public ResponseDTO<String> excluirVaga(@PathVariable("id") Long id) {
        try {
            usuarioService.excluirVaga(id); // Chama o método no UsuarioService
            return ResponseDTO.<String>builder()
                    .message("Vaga excluída com sucesso!")
                    .status(200)
                    .build();
        } catch (Exception e) {
            return ResponseDTO.<String>builder()
                    .message("Erro ao excluir a vaga: " + e.getMessage())
                    .status(400)
                    .build();
        }
    }
}
