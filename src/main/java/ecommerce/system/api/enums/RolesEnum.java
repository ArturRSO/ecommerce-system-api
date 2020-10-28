package ecommerce.system.api.enums;

public enum RolesEnum {
    SYSTEM_ADMIN (1, "system_admin"),
    STORE_ADMIN (2, "store_admin"),
    CUSTOMER (3, "customer"),
    GUEST (4, "guest");

    private final int id;
    private final String name;

    RolesEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getRoleById(int id) {
        for (RolesEnum e : RolesEnum.values()) {
            if(e.getId() == id) {
                return e.getName();
            }
        }

        return null;
    }
}
