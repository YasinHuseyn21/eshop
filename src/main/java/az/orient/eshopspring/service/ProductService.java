package az.orient.eshopspring.service;

import az.orient.eshopspring.dto.response.RespProduct;
import az.orient.eshopspring.dto.response.Response;

import java.util.List;

public interface ProductService {
    Response<List<RespProduct>> getAllProduct();

}
