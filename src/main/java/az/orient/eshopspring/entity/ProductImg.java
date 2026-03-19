package az.orient.eshopspring.entity;
import lombok.Data;

@Data
public class ProductImg {
    private Integer id;
    private Products product;
    private String imgUrl;
}
