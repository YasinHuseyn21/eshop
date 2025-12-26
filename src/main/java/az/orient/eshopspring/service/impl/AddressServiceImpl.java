package az.orient.eshopspring.service.impl;

import az.orient.eshopspring.dto.request.ReqAddress;
import az.orient.eshopspring.dto.response.RespAddress;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.dto.response.ResponseStatus;
import az.orient.eshopspring.entity.Address;
import az.orient.eshopspring.entity.Users;
import az.orient.eshopspring.enums.EnumStatus;
import az.orient.eshopspring.exception.ExceptionConst;
import az.orient.eshopspring.exception.ShopException;
import az.orient.eshopspring.repo.DataAddressRepo;
import az.orient.eshopspring.repo.DataUserRepo;
import az.orient.eshopspring.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final DataAddressRepo dataAddressRepo;
    private final DataUserRepo dataUserRepo;
    private final UserServiceImpl userServiceImpl;


    @Override
    public Response<List<RespAddress>> getAllAddress(Long usersId) {
        Response<List<RespAddress>> response = new Response<>();
        List<RespAddress> respAddressList = new ArrayList<>();
        try {
            Users users = dataUserRepo.findUsersByIdAndActive(usersId, EnumStatus.ACTIVE.getValue());
            if (users == null) {
                throw new ShopException(ExceptionConst.USER_NOT_FOUND, "User not found");
            }
            List<Address> addressList = dataAddressRepo.findAllByUserAndActive(users, EnumStatus.ACTIVE.getValue());
            if (addressList == null) {
                throw new ShopException(ExceptionConst.ADDRESS_NOT_FOUND, "Address not found");
            }
            Iterator<Address> addressIterator = addressList.iterator();
            while (addressIterator.hasNext()) {
                Address address = addressIterator.next();
                RespAddress respAddress = fillAddress(address);
                respAddressList.add(respAddress);
            }
            response.setT(respAddressList);
            response.setStatus(ResponseStatus.success());
        } catch (ShopException e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, e.getMessage()));
        }

        return response;
    }

    @Override
    public Response<RespAddress> getAddressById(Long addressId) {
        Response<RespAddress> response = new Response<>();
        try {
            if (addressId == null) {
                throw new ShopException(ExceptionConst.ADDRESS_NOT_FOUND, "Address not found");
            }
            Address address = dataAddressRepo.findAddressByIdAndActive(addressId, EnumStatus.ACTIVE.getValue());
            if (address == null) {
                throw new ShopException(ExceptionConst.ADDRESS_NOT_FOUND, "Address not found");
            }
            response.setT(fillAddress(address));
            response.setStatus(ResponseStatus.success());
        } catch (ShopException e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, e.getMessage()));
        }

        return response;
    }

    @Override
    public Response<RespAddress> createAddress(ReqAddress reqAddress) {
        Response<RespAddress> response = new Response<>();
        try {
            if (reqAddress == null) {
                throw new ShopException(ExceptionConst.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Long id = reqAddress.getUsersId();
            Users user = dataUserRepo.findUsersByIdAndActive(id, EnumStatus.ACTIVE.getValue());
            if (user == null) {
                throw new ShopException(ExceptionConst.USER_NOT_FOUND, "User not found");
            }
            Address address = Address.builder()
                    .user(user)
                    .addressLine(reqAddress.getAddressLine())
                    .city(reqAddress.getCity())
                    .postalCode(reqAddress.getPostalCode())
                    .country(reqAddress.getCountry())
                    .build();
            dataAddressRepo.save(address);
            response.setT(fillAddress(address));
            response.setStatus(ResponseStatus.success());
        } catch (ShopException e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, e.getMessage()));
        }
        return response;
    }

    @Override
    public Response deleteAddress(Long id) {
        Response<RespAddress> response = new Response<>();
        try {
            Address address = dataAddressRepo.findAddressByIdAndActive(id, EnumStatus.ACTIVE.getValue());
            if (address == null) {
                throw new ShopException(ExceptionConst.ADDRESS_NOT_FOUND, "Address not found");
            }
            address.setActive(EnumStatus.DEACTIV.getValue());
            dataAddressRepo.save(address);
            response.setStatus(ResponseStatus.success());
        } catch (ShopException e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, e.getMessage()));
        }
        return response;
    }


    public RespAddress fillAddress(Address address) {
        return RespAddress.builder()
                .id(address.getId())
                .addressLine(address.getAddressLine())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .respUsers(userServiceImpl.fill(address.getUser()))
                .build();
    }
}
