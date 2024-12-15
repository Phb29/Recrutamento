package paulo.recrutamentointerno.domain.model.dto;

import lombok.Data;

@Data
public class AcessDTO {

    private String token;
    private Long status;
    private Long id;


    public AcessDTO(String token, Long id) {
        this.token = token;
        this.id = id;
        this.status = Long.valueOf(200);
    }


    public AcessDTO(String token) {
        this.token = token;
        this.status = Long.valueOf(200);
    }
}
