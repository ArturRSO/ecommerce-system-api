package ecommerce.system.api.models;

public class TelephoneTypeModel {

    private int documentTypeId;
    private String name;

    public TelephoneTypeModel(int documentTypeId, String name) {
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
