package az.orient.eshopspring.repo;

import az.orient.eshopspring.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataUserRepo extends JpaRepository<Users, Integer> {

    List<Users> findUsersByActive(Integer active);

    Users findUsersByIdAndActive(Long id ,Integer active);

}
