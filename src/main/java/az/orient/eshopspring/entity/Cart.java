package az.orient.eshopspring.entity;
import az.orient.eshopspring.entity.enums.CartStatus;
import lombok.Data;
import java.util.Date;


@Data
public class Cart {
    private Long id;
    private Users userId;
    private CartStatus cartStatus;
    private double totalAmount;
    private Date createdAt;


}
