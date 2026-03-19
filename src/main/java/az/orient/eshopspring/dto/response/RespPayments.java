package az.orient.eshopspring.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class RespPayments {
    private RespCart respCart;
    private int paymentMethod;
    private int paymentStatus;
    private Date paymentDate;
}
