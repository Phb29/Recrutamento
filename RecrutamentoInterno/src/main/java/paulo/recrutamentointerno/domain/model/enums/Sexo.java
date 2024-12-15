package paulo.recrutamentointerno.domain.model.enums;

public enum Sexo {
    M("Masculino", "M"),
    F("Feminino", "F");

    private String label;
    private String value;

    private Sexo(String label, String value) {
        this.label = label;
        this.value = value;
    }

}
