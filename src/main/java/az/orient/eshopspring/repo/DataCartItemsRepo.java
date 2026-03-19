package az.orient.eshopspring.repo;

import az.orient.eshopspring.entity.Cart;
import az.orient.eshopspring.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataCartItemsRepo extends JpaRepository<CartItems, Integer> {
    List<CartItems> findCartItemsByActive(int active);
    List<CartItems> findByCartIdAndActive(Integer cartId, Integer active);
    CartItems findCartItemsById(Integer id);
    CartItems findByCartIdAndProductIdAndActive(Integer cartId, Long productId, Integer active);

}
