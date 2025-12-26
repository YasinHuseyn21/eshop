package az.orient.eshopspring.entity;

import az.orient.eshopspring.entity.enums.PaymentMethod;
import az.orient.eshopspring.entity.enums.PaymentStatus;

import java.util.Date;

public class Payments {

    private Long id;
    private Cart cartId;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Date paidAt;

}
