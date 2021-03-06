package ecommerce.system.api.enums;

public enum PaymentMethodEnum {
    CREDIT_CARD (1, "Cartão de Crédito"),
    DEBT_CARD(2, "Cartão de débito");

    private final int id;
    private final String name;

    PaymentMethodEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static PaymentMethodEnum getPaymentMethodById(int id) {
         for (PaymentMethodEnum e : PaymentMethodEnum.values()) {
             if (e.getId() == id) {
                 return e;
             }
         }

         return null;
    }
}
