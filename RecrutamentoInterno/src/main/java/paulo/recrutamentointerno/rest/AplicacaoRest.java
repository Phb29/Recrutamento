package paulo.recrutamentointerno.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paulo.recrutamentointerno.domain.model.dto.AplicacaoDTO;
import paulo.recrutamentointerno.domain.model.dto.ResponseDTO;
import paulo.recrutamentointerno.domain.model.dto.UsuarioAplicadoDTO;
import paulo.recrutamentointerno.service.AplicacaoService;
import paulo.recrutamentointerno.service.VagaService;

import java.util.List;

@RestController
@RequestMapping("/apply")
@CrossOrigin
public class AplicacaoRest {

    @Autowired
    private AplicacaoService bo;

    @Autowired
    private VagaService bo2;

    @PostMapping("/save")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseDTO<AplicacaoDTO> saveAplicacao(@RequestBody AplicacaoDTO dto) {
        return bo.saveAplicacao(dto);
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseDTO<List<UsuarioAplicadoDTO>> buscaVagasCandidatos() {
        return bo2.buscaVagasCandidatos();
    }

}
