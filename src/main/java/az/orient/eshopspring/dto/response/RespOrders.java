package az.orient.eshopspring.dto.response;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class RespOrders {

    private Integer id;
    private RespCart respCart;
    private double price;
    private int quantity;
}
