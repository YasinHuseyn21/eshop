package az.orient.eshopspring.entity;

import lombok.Data;

@Data
public class OrderItems {
    private int id;
    private Orders order;
    private Products productId;
    private int quantity;
    private double price;
}
