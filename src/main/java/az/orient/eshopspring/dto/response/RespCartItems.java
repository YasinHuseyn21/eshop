package az.orient.eshopspring.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespCartItems {

    private Integer id;
//    private RespCart respCart;
    private RespProduct respProduct;
    private int quantity;
}
