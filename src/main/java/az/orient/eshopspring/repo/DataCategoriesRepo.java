package az.orient.eshopspring.repo;

import az.orient.eshopspring.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataCategoriesRepo extends JpaRepository<Categories, Integer> {

    List<Categories> findCategoriesByActive(int active);
    Categories findCategoriesById(Integer id);
    Categories findCategoriesByName(String name);

}
