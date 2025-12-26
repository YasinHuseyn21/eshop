package az.orient.eshopspring.controller;

import az.orient.eshopspring.dto.request.ReqAddress;
import az.orient.eshopspring.dto.response.RespAddress;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.entity.Address;
import az.orient.eshopspring.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @GetMapping("/list")
    public Response<List<RespAddress>> getAllAddress(@RequestParam("usersId") Long usersId) {
        return addressService.getAllAddress(usersId);
    }

    @GetMapping("/listById/{addressId}")
    public Response<RespAddress> getAddress(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @PostMapping("/create")
    public Response<RespAddress> createAddress(@RequestBody ReqAddress reqAddress) {
    return addressService.createAddress(reqAddress);
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteAddress(@PathVariable Long id) {
        return addressService.deleteAddress(id);
    }
}
