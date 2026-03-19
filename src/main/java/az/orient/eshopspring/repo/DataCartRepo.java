package az.orient.eshopspring.repo;

import az.orient.eshopspring.entity.Cart;
import az.orient.eshopspring.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataCartRepo extends JpaRepository<Cart,Integer> {

    List<Cart> findCartByActive(Integer active);
    Cart findCartById(Integer id);
    Cart findByUserIdAndActive(Users user, Integer active);
}
