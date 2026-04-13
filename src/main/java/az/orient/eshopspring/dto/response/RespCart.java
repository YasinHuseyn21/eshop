package az.orient.eshopspring.dto.response;

import az.orient.eshopspring.enums.CartStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class RespCart {

    private Integer id;
    private BigDecimal totalPrice;
    private CartStatus cartStatus;
    private List<RespCartItems> respCartItems;
    private RespUsers respUsers;
    private Date date;

}
