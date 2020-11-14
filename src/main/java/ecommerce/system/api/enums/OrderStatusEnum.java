package ecommerce.system.api.enums;

public enum OrderStatusEnum {

    RECEIVED(1, "Pedido recebido"),
    PAID(2, "Pagamento confirmado"),
    SENT(3, "Pedido enviado"),
    FINISHED(4, "Pedido finalizado");

    private final int id;
    private final String name;

    OrderStatusEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
