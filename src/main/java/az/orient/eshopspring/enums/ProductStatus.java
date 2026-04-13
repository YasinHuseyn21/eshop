package az.orient.eshopspring.enums;

public enum ProductStatus {
    ACTIVE("ACTIVE"),       // Məhsul aktivdir, satış üçün əlçatandır
    INACTIVE("INACTIVE"),     // Məhsul müvəqqəti deaktivdir, görünmür
    OUT_OF_STOCK("OUT_OF_STOCK"), // Məhsul tükənib
    DELETED("DELETED");

    private String status;

    ProductStatus(String code) {
        this.status = status;
    }

    public static ProductStatus fromString(String status) {
        for (ProductStatus productStatus : values()) {
            if (productStatus.name().equalsIgnoreCase(status))
                return productStatus;
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }

}
