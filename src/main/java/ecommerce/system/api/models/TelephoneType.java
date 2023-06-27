package ecommerce.system.api.models;

public class TelephoneType {

    private int documentTypeId;
    private String name;

    public TelephoneType(int documentTypeId, String name) {
        this.documentTypeId = documentTypeId;
        this.name = name;
    }

    public int getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
