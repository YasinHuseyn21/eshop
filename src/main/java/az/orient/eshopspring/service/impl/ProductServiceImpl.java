package az.orient.eshopspring.service.impl;

import az.orient.eshopspring.dto.response.RespProduct;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.dto.response.ResponseStatus;
import az.orient.eshopspring.entity.Products;
import az.orient.eshopspring.enums.ProductStatus;
import az.orient.eshopspring.enums.EnumStatus;
import az.orient.eshopspring.exception.ExceptionConst;
import az.orient.eshopspring.exception.ShopException;
import az.orient.eshopspring.repo.DataProductRepo;
import az.orient.eshopspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final DataProductRepo dataProductRepo;

    @Override
    public Response<List<RespProduct>> getAllProduct() {
        Response<List<RespProduct>> response = new Response<>();
        try {
            List<Products> productList = dataProductRepo.findProductsByActive(EnumStatus.ACTIVE.getValue());
            if (productList.isEmpty()) {
                throw new ShopException(ExceptionConst.PRODUCT_NOT_FOUND, "Product not found");
            }
            List<RespProduct> respProducts = productList.stream().map(this::convert).toList();
            response.setT(respProducts);
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


    public RespProduct convert(Products product) {
        return RespProduct.builder()
                .id(product.getId())
                .respCategories(CategoriesServiceImpl.convert(product.getCategoryId()))
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(ProductStatus.fromString(product.getStatus()))
                .createdAt(product.getCreatedAt()).build();
    }


}
