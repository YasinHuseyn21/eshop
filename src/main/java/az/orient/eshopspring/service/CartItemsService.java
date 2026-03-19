package az.orient.eshopspring.service;

import az.orient.eshopspring.dto.request.ReqCartItems;
import az.orient.eshopspring.dto.response.RespCartItems;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.entity.Cart;
import az.orient.eshopspring.entity.CartItems;
import az.orient.eshopspring.entity.Products;

import java.util.List;

public interface CartItemsService {

    Response<List<RespCartItems>> getAllCartItems();

    Response<RespCartItems> getCartItemsById(Integer id);

    Response<CartItems> createCartItems(Cart cart, Integer quantity, Products products);

    Response<RespCartItems> delete(CartItems cartItems);
}

