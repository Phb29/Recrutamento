package paulo.recrutamentointerno.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paulo.recrutamentointerno.domain.model.dataService.impl.VagaInterfaceDAO;
import paulo.recrutamentointerno.domain.model.dto.ResponseDTO;
import paulo.recrutamentointerno.domain.model.dto.VagaDTO;
import paulo.recrutamentointerno.service.VagaService;

import java.util.List;

@RestController
@RequestMapping("/vaga")
public class VagaRest {

    @Autowired
    VagaService bo = new VagaService();

    @Autowired
    private VagaInterfaceDAO vagaInterfaceDAO;

    @PostMapping("/save")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseDTO<VagaDTO> saveClient(@RequestBody VagaDTO vaga) {
        System.out.println("ID Recrutador recebido: " + vaga.getIdRecrutador());

        if (vaga.getIdRecrutador() == null) {
            throw new RuntimeException("ID do recrutador não foi enviado.");
        }

        return bo.saveVaga(vaga, vaga.getIdRecrutador(), vagaInterfaceDAO);
    }



    @GetMapping("/list")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseDTO<List<VagaDTO>> getAllVagas() {
        return bo.getVagas();
    }



}
