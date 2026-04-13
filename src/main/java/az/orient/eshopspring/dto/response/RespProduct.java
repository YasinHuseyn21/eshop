package az.orient.eshopspring.dto.response;

import az.orient.eshopspring.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Builder
public class RespProduct {

    private Integer id;
    private RespCategories respCategories;
    private String name;
    private String description;
    private BigDecimal price;
    private ProductStatus status;
    private Date createdAt;

}
