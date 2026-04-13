package az.orient.eshopspring.service.impl;

import az.orient.eshopspring.dto.request.ReqCart;
import az.orient.eshopspring.dto.request.ReqOrder;
import az.orient.eshopspring.dto.response.RespOrders;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public Response<List<RespOrders>> getAllOrders() {
        return null;
    }

    @Override
    public Response<RespOrders> getOrdersById(Integer id) {
        return null;
    }

    @Override
    public Response<RespOrders> createOrder(Long userId) {
return  null;
    }

    @Override
    public Response<RespOrders> addToOrder(ReqOrder reqOrder) {
        return null;
    }

    @Override
    public Response<RespOrders> removeProductFromOrder(ReqCart reqCart) {
        return null;
    }
}
