package az.orient.eshopspring.entity;
import lombok.Data;

@Data
public class CartItems {
    private int id;
    private Cart cart;
    private Products product;
    private int quantity;
    private int active;
}
