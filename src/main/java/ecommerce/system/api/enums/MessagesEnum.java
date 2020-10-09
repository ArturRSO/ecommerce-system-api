package ecommerce.system.api.enums;

public enum MessagesEnum {
    FAILURE("Ocorreu um erro. Tente novamente mais tarde."),
    SUCCESS("Operação concluída com sucesso!"),
    NOT_FOUND("Nenhum resultado encontrado!");

    private final String message;

    MessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
