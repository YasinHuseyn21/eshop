package az.orient.eshopspring.service.impl;

import az.orient.eshopspring.dto.request.ReqCart;
import az.orient.eshopspring.dto.response.RespCart;
import az.orient.eshopspring.dto.response.RespCartItems;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.dto.response.ResponseStatus;
import az.orient.eshopspring.entity.Cart;
import az.orient.eshopspring.entity.CartItems;
import az.orient.eshopspring.entity.Products;
import az.orient.eshopspring.entity.Users;
import az.orient.eshopspring.entity.enums.CartStatus;
import az.orient.eshopspring.enums.EnumStatus;
import az.orient.eshopspring.exception.ExceptionConst;
import az.orient.eshopspring.exception.ShopException;
import az.orient.eshopspring.repo.DataCartItemsRepo;
import az.orient.eshopspring.repo.DataCartRepo;
import az.orient.eshopspring.repo.DataProductRepo;
import az.orient.eshopspring.repo.DataUserRepo;
import az.orient.eshopspring.service.CartItemsService;
import az.orient.eshopspring.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final DataCartRepo dataCartRepo;
    private final UserServiceImpl userServiceImpl;
    private final DataCartItemsRepo dataCartItemsRepo;
    private final ProductServiceImpl productServiceImpl;
    private final DataUserRepo dataUserRepo;
    private final DataProductRepo dataProductRepo;
    private final CartItemsService cartItemsService;


    @Override
    public Response<List<RespCart>> getAllCart() {
        Response<List<RespCart>> response = new Response<>();
        List<RespCart> cartList = new ArrayList<>();
        try {
            List<Cart> listCart = dataCartRepo.findCartByActive(EnumStatus.ACTIVE.getValue());
            if (listCart == null) {
                throw new ShopException(ExceptionConst.CART_NOT_FOUND, "Cart not found");
            }
            for (Cart cart : listCart) {
                List<CartItems> cartItems = dataCartItemsRepo.findByCartIdAndActive(cart.getId(), EnumStatus.ACTIVE.getValue());
                cart.setCartItems(cartItems);

                BigDecimal totalAmount = cartItems.stream()
                        .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                cart.setTotalAmount(totalAmount);
                cartList.add(convert(cart));
            }
            response.setT(cartList);
            response.setStatus(ResponseStatus.success());
        } catch (ShopException ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, ex.getMessage()));
        }
        return response;
    }

    @Override
    public Response<RespCart> getCartById(Integer id) {
        Response<RespCart> response = new Response<>();
        try {
            if (id == null) {
                throw new ShopException(ExceptionConst.INVALID_REQUEST_DATA, "INVALID REQUEST DATA");
            }
            Cart cart = dataCartRepo.findCartById(id);
            if (cart == null) {
                throw new ShopException(ExceptionConst.CART_NOT_FOUND, "Cart not found");
            }
            response.setT(convert(cart));
            response.setStatus(ResponseStatus.success());

        } catch (ShopException ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, ex.getMessage()));
        }

        return response;
    }

    @Override
    public Response<RespCart> reqAddToCart(ReqCart reqAddToCart) {
        Response<RespCart> response = new Response<>();
        try {
            Users user = dataUserRepo.findUsersByIdAndActive(reqAddToCart.userId(), EnumStatus.ACTIVE.getValue());
            if (user == null) {
                throw new ShopException(ExceptionConst.USER_NOT_FOUND, "USER NOT FOUND");
            }
            if (dataCartRepo.findByUserIdAndActive(user, EnumStatus.ACTIVE.getValue()) == null) {
                createCart(reqAddToCart.userId());
            }

            Cart cartNew = dataCartRepo.findByUserIdAndActive(user, EnumStatus.ACTIVE.getValue());
            CartItems cartItem = dataCartItemsRepo.findByCartIdAndProductIdAndActive(cartNew.getId()
                    , reqAddToCart.productId()
                    , EnumStatus.ACTIVE.getValue());

            Optional<Products> products = Optional.ofNullable(dataProductRepo.findById(reqAddToCart.productId())
                    .orElseThrow(() -> new ShopException(ExceptionConst.PRODUCT_NOT_FOUND, "PRODUCT NOT FOUND")));


            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + reqAddToCart.quantity());
                dataCartItemsRepo.save(cartItem);
            } else {
                cartItemsService.createCartItems(cartNew, reqAddToCart.quantity(), products.get());
//                cartItem = new CartItems();
//                cartItem.setCart(cartNew);
//                cartItem.setProduct(products.get());
//                cartItem.setQuantity(reqAddToCart.quantity());
//                cartNew.getCartItems().add(cartItem);
//                dataCartItemsRepo.save(cartItem);

            }

            response.setT(convert(cartNew));
            response.setStatus(ResponseStatus.success());
        } catch (ShopException ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, ex.getMessage()));
        }
        return response;
    }


    @Override
    public Response<RespCart> createCart(Long userId) {
        Response<RespCart> response = new Response<>();
        try {
            if (userId == null) {
                throw new ShopException(ExceptionConst.INVALID_REQUEST_DATA, "INVALID REQUEST DATA");
            }
            Users user = dataUserRepo.findUsersByIdAndActive(userId, EnumStatus.ACTIVE.getValue());
            if (user == null) {
                throw new ShopException(ExceptionConst.USER_NOT_FOUND, "USER NOT FOUND");
            }
            Cart cart = dataCartRepo.findByUserIdAndActive(user, EnumStatus.ACTIVE.getValue());
            if (cart == null) {
                cart = Cart.builder()
                        .userId(user)
                        .cartStatus(CartStatus.ACTIVE.getStatus())
                        .cartItems(new ArrayList<>())
                        .build();
                cart = dataCartRepo.save(cart);
            }
            response.setStatus(ResponseStatus.success());
        } catch (ShopException ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, ex.getMessage()));
        }
        return response;
    }

    @Override
    public Response<RespCart> deleteCategoryById(Integer id) {
        return null;
    }

    @Override
    public Response<RespCart> removeProductFromCart(ReqCart reqCart) {
        Response<RespCart> response = new Response<>();
        try {
            Users user = dataUserRepo.findUsersByIdAndActive(reqCart.userId(), EnumStatus.ACTIVE.getValue());
            Cart cart = dataCartRepo.findByUserIdAndActive(user, EnumStatus.ACTIVE.getValue());
            CartItems item = dataCartItemsRepo.findByCartIdAndProductIdAndActive(cart.getId(), reqCart.productId(), EnumStatus.ACTIVE.getValue());
            if (item == null) {
                throw new ShopException(ExceptionConst.CART_ITEM_NOT_FOUND, "CART ITEM NOT FOUND");
            } else {
                cartItemsService.delete(item);}
            response.setStatus(ResponseStatus.success());
        } catch (ShopException ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, ex.getMessage()));
        }
        return response;
    }


    public RespCart convert(Cart cart) {

        List<RespCartItems> respItems = cart.getCartItems().stream()
                .map(item -> RespCartItems.builder()
                        .id(item.getProduct().getId())
                        .respProduct(productServiceImpl.convert(item.getProduct()))
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
        return RespCart.builder()
                .id(cart.getId())
                .totalPrice(cart.getTotalAmount())
                .cartStatus(CartStatus.fromString(cart.getCartStatus()))
                .respUsers(userServiceImpl.fill(cart.getUserId()))
                .respCartItems(respItems)
                .date(cart.getCreatedAt()).build();
    }
}
