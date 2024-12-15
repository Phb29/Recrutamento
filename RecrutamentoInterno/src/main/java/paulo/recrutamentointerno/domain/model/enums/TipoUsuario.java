package paulo.recrutamentointerno.domain.model.enums;

public enum TipoUsuario {
    recruiter("Recrutador"),
    candidate("Candidato");

    private String label;


    private TipoUsuario(String label) {
        this.label = label;
    }


}
