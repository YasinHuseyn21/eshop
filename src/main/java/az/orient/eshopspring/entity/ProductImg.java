package az.orient.eshopspring.entity;
import lombok.Data;

@Data
public class ProductImg {
    private int id;
    private Products product;
    private String imgUrl;
}
