package az.orient.eshopspring.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespOrderItems {
   private Integer id;
   private RespOrders respOrders;
   private double price;
   private RespProduct respProduct;
   private int quantity;

}
