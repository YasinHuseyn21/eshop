package az.orient.eshopspring.service.impl;

import az.orient.eshopspring.dto.response.RespCartItems;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.dto.response.ResponseStatus;
import az.orient.eshopspring.entity.Cart;
import az.orient.eshopspring.entity.CartItems;
import az.orient.eshopspring.entity.Products;
import az.orient.eshopspring.enums.EnumStatus;
import az.orient.eshopspring.exception.ExceptionConst;
import az.orient.eshopspring.exception.ShopException;
import az.orient.eshopspring.repo.DataCartItemsRepo;
import az.orient.eshopspring.service.CartItemsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemsServiceImpl implements CartItemsService {
    private final DataCartItemsRepo dataCartItemsRepo;

    @Override
    public Response<List<RespCartItems>> getAllCartItems() {
        return null;
    }

    @Override
    public List<CartItems> getCartItemsById(Integer id) {
        List<CartItems> items = dataCartItemsRepo.findByCartIdAndActive(id, EnumStatus.ACTIVE.getValue());
        if (items == null) {
            throw new ShopException(ExceptionConst.CART_ITEM_NOT_FOUND, "CART_ITEM NOT FOUND");
        }
        return items;
    }

    @Override
    public Response<CartItems> createCartItems(Cart cart, Integer quantity, Products products) {
        Response<CartItems> response = new Response<>();
        try {
            CartItems cartItem = new CartItems();
            cartItem.setCart(cart);
            cartItem.setProduct(products);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);
            dataCartItemsRepo.save(cartItem);

        } catch (ShopException e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INVALID_REQUEST_DATA, "INTERNAL SERVER ERROR"));
        }
        return response;
    }

    @Override
    public Response<RespCartItems> delete(CartItems items) {
        Response<RespCartItems> response = new Response<>();
        try {
            if (items == null) {
                throw new ShopException(ExceptionConst.INVALID_REQUEST_DATA, "INVALID REQUEST DATA");
            }
            CartItems cartItems = dataCartItemsRepo.findCartItemsById(items.getId());
            cartItems.setActive(EnumStatus.DEACTIV.getValue());
            dataCartItemsRepo.save(cartItems);
            response.setStatus(ResponseStatus.success());
        } catch (ShopException ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, "INTERNAL SERVER ERROR"));
        }
        return response;
    }
}
