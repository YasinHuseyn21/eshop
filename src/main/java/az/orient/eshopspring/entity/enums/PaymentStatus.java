package az.orient.eshopspring.entity.enums;

public enum PaymentStatus {
    PENDING,   // Ödəniş gözləmədədir
    PAID,      // Uğurla ödənib
    FAILED,    // Ödəniş alınmadı
    CANCELED,  // İstifadəçi və ya sistem tərəfindən ləğv olunub
    REFUNDED
}
