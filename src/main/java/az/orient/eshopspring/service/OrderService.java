package az.orient.eshopspring.service;

import az.orient.eshopspring.dto.request.ReqCart;
import az.orient.eshopspring.dto.request.ReqOrder;
import az.orient.eshopspring.dto.response.RespOrders;
import az.orient.eshopspring.dto.response.Response;

import java.util.List;

public interface OrderService {

    Response<List<RespOrders>> getAllOrders();

    Response<RespOrders> getOrdersById(Integer id);

    Response<RespOrders> createOrder(Long CartId);

    Response<RespOrders> addToOrder(ReqOrder reqOrder);

    Response<RespOrders> removeProductFromOrder(ReqCart reqCart);
}
