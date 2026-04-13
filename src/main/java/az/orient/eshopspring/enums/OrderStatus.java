package az.orient.eshopspring.enums;

public enum OrderStatus {
    NEW("NEW"),             // Checkout basılıb, amma ödəniş tamamlanmayıb
    PAID("PAID"),           // Ödəniş uğurla tamamlanıb, emal üçün hazırdır
    SHIPPED("SHIPPED"),     // Sifariş göndərilib, çatdırılma gözlənilir
    DELIVERED("DELIVERED"), // Müştəriyə çatdırılıb
    CANCELLED("CANCELLED"), // Sifariş ləğv olunub
    REFUNDED("REFUNDED");   // Məhsul geri qaytarılıb, pul geri ödənilib
    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatus fromString(String status) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.getStatus().equalsIgnoreCase(status))
                return orderStatus;
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
