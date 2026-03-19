package az.orient.eshopspring.controller;

import az.orient.eshopspring.dto.request.ReqCart;
import az.orient.eshopspring.dto.response.RespCart;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    public final CartService cartService;


    @GetMapping("/listCart")
    public Response<List<RespCart>> getCart() {
        return cartService.getAllCart();
    }

    @GetMapping("/listById")
    public Response<RespCart> getCartById(@RequestParam("cartId") Integer id) {
        return cartService.getCartById(id);
    }

    @PostMapping("/add")
    public Response<RespCart> addToCart(@RequestBody ReqCart reqAddToCart) {
        return cartService.reqAddToCart(reqAddToCart);
    }

    @PostMapping("/remove")
    public Response<RespCart> removeFromCart(@RequestBody ReqCart reqCart) {
        return  cartService.removeProductFromCart(reqCart);
    }
}
