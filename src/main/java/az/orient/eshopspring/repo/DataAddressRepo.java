package az.orient.eshopspring.repo;

import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.entity.Address;
import az.orient.eshopspring.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataAddressRepo extends JpaRepository<Address, Integer> {
    Address findAddressByIdAndActive(Long addressId,Integer active);
    List<Address> findAllByUserAndActive(Users users, Integer active);

}
