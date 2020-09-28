package ecommerce.system.api.enums;

public enum DocumentTypeEnum {
    CPF (1, "CPF"),
    CNPJ (2, "CNPJ");

    private final int id;
    private final String name;

    DocumentTypeEnum(int id, String name) {
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
        for (DocumentTypeEnum e : DocumentTypeEnum.values()) {
            if(e.getId() == id) {
                return e.getName();
            }
        }

        return null;
    }
}
