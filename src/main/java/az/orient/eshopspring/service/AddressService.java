package az.orient.eshopspring.service;

import az.orient.eshopspring.dto.request.ReqAddress;
import az.orient.eshopspring.dto.response.RespAddress;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    Response<List<RespAddress>> getAllAddress(Long usersId);

    Response<RespAddress> getAddressById(Long addresstId);

    Response<RespAddress> createAddress(ReqAddress reqAddress);

    Response deleteAddress(Long id);
}
