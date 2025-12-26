package az.orient.eshopspring.entity;

import lombok.Data;

@Data
public class Orders {

    private Long id;
    private Long quantity;
    private double price;
    private Cart cart;
}
