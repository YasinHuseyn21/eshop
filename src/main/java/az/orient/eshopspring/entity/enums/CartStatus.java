package az.orient.eshopspring.entity.enums;

public enum CartStatus {
    ACTIVE("ACTIVE"),     // İstifadəçi səbəti istifadə edir, tamamlanmayıb
    ORDERED("ORDERED"),    // Checkout edilib, sifarişə çevrilib
    EXPIRED ("EXPIRED") ;   // Uzun müddət istifadə olunmayıb, avtomatik silinə bilər

    private String status;

    CartStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
            return status;
        }

    public static CartStatus fromString(String status) {
        for (CartStatus cartStatus : values()) {
            if (cartStatus.getStatus().equalsIgnoreCase(status))
                return cartStatus;
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }


}
