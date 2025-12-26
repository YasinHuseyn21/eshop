package az.orient.eshopspring.entity.enums;

public enum CartStatus {
    ACTIVE("ACTIVE"),     // İstifadəçi səbəti istifadə edir, tamamlanmayıb
    ORDERED("ORDERED"),    // Checkout edilib, sifarişə çevrilib
    CANCELLED("CANCELLED"),  // Müştəri və ya sistem tərəfindən ləğv edilib
    EXPIRED ("EXPIRED") ;   // Uzun müddət istifadə olunmayıb, avtomatik silinə bilər

    private String status;

    CartStatus(String code) {
        this.status = status;
    }

    public static CartStatus fromString(String status) {
        for (CartStatus cartStatus : values()) {
            if (cartStatus.name().equalsIgnoreCase(status))
                return cartStatus;
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
