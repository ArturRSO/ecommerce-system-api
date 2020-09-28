package ecommerce.system.api.enums;

public enum TelephoneTypeEnum {
    CELULAR (1, "Celular"),
    TELEFONE_RESIDENCIAL (2, "Telefone residencial"),
    TELEFONE_COMERCIAL (3, "Telefone comercial");

    private final int id;
    private final String name;

    TelephoneTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getDocumentTypeById(int id) {
        for (TelephoneTypeEnum e : TelephoneTypeEnum.values()) {
            if(e.getId() == id) {
                return e.getName();
            }
        }

        return null;
    }
}
