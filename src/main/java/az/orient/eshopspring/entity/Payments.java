package az.orient.eshopspring.entity;

import az.orient.eshopspring.entity.enums.PaymentMethod;
import az.orient.eshopspring.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Payments {
    @Id
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cartId;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Date paidAt;

}
