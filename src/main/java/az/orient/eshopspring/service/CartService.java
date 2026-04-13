package az.orient.eshopspring.service;

import az.orient.eshopspring.dto.request.ReqCart;
import az.orient.eshopspring.dto.response.RespCart;
import az.orient.eshopspring.dto.response.Response;

import java.util.List;

public interface CartService {

    Response<List<RespCart>> getAllCart();

    Response<RespCart> getCartById(Integer id);

    Response<RespCart> createCart(Long userId);

    Response<RespCart> addToCart(ReqCart reqAddToCart);

    Response<RespCart> removeProductFromCart(ReqCart reqCart);
}
