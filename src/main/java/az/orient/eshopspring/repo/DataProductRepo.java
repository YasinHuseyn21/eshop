package az.orient.eshopspring.repo;

import az.orient.eshopspring.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataProductRepo extends JpaRepository<Products, Long> {
    List<Products> findProductsByActive(Integer active);

}
