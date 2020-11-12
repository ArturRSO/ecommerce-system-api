package ecommerce.system.api.enums;

public enum TelephoneTypeEnum {
    PERSONAL_CELLPHONE (1, "Celular pessoal"),
    PERSONAL_TELEPHONE (2, "Telefone pessoal"),
    COMMERCIAL_CELLPHONE (3, "Celular comercial"),
    COMMERCIAL_TELEPHONE  (4, "Telefone comercial");

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
