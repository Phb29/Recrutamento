package paulo.recrutamentointerno.service;


import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import paulo.recrutamentointerno.domain.model.Usuario;
import paulo.recrutamentointerno.domain.model.Vaga;
import paulo.recrutamentointerno.domain.model.dataService.impl.VagaDAO;
import paulo.recrutamentointerno.domain.model.dataService.impl.VagaInterfaceDAO;
import paulo.recrutamentointerno.domain.model.dto.ResponseDTO;
import paulo.recrutamentointerno.domain.model.dto.UsuarioAplicadoDTO;
import paulo.recrutamentointerno.domain.model.dto.VagaDTO;
import paulo.recrutamentointerno.repository.UsuarioRepository;

import java.util.List;

import static javax.ws.rs.core.Response.Status.*;
@Service
public class VagaService {

    @Autowired
    private VagaDAO dao;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseDTO<VagaDTO> saveVaga(VagaDTO formDTO, Long idRecrutador, VagaInterfaceDAO vagaInterfaceDAO) {
        if (formDTO.getId() == null) {
            try {
                Usuario recrutador = usuarioRepository.findById(idRecrutador)
                        .orElseThrow(() -> new RuntimeException("Recrutador não encontrado"));

                Vaga vaga = formDTO.toVagaEntity();
                vaga.setCreatedBy(recrutador);

                vaga = vagaInterfaceDAO.save(vaga);
                formDTO.setId(vaga.getId());

            } catch (Exception e) {
                return tratarExcecoes(e, formDTO);
            }

            return ResponseDTO.<VagaDTO>builder()
                    .data(formDTO)
                    .message("Vaga salva")
                    .description("A vaga " + formDTO.getTitulo() + " foi salva com sucesso")
                    .status(OK.getStatusCode())
                    .build();
        } else {
            VagaDTO formDTOup = dao.upgrade(formDTO);
            return ResponseDTO.<VagaDTO>builder()
                    .data(formDTOup)
                    .message("Vaga atualizada")
                    .description("A vaga " + formDTO.getTitulo() + " foi atualizada com sucesso")
                    .status(OK.getStatusCode())
                    .build();
        }
    }

    private ResponseDTO<VagaDTO> tratarExcecoes(Exception e, VagaDTO formDTO) {
        if (e instanceof DataIntegrityViolationException) {
            PropertyValueException cve2 = (PropertyValueException) e.getCause();
            return ResponseDTO.<VagaDTO>builder()
                    .data(formDTO)
                    .message("Erro ao processar a requisição")
                    .description("Erro na propriedade " + cve2.getPropertyName())
                    .status(BAD_REQUEST.getStatusCode())
                    .build();
        }
        return ResponseDTO.<VagaDTO>builder()
                .data(formDTO)
                .message("Erro ao processar a requisição")
                .status(BAD_REQUEST.getStatusCode())
                .build();
    }


    public ResponseDTO<List<VagaDTO>> getVagas() {
        List<VagaDTO> list = dao.busca();
        return ResponseDTO.<List<VagaDTO>>builder()
                .data(list)
                .message("List Clients")
                .description("Successfully Listed Clients")
                .status(OK.getStatusCode())
                .build();
    }

    public ResponseDTO<List<UsuarioAplicadoDTO>> buscaVagasCandidatos() {
        List<UsuarioAplicadoDTO> list = dao.buscaVagasCandidatos();
        return ResponseDTO.<List<UsuarioAplicadoDTO>>builder()
                .data(list)
                .message("List Clients")
                .description("Successfully Listed Clients")
                .status(OK.getStatusCode())
                .build();
    }
}
