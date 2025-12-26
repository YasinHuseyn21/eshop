package az.orient.eshopspring.repo;

import az.orient.eshopspring.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface DataCategoriesRepo extends JpaRepository<Categories, Long> {
}
