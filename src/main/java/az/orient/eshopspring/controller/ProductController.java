package az.orient.eshopspring.controller;
import az.orient.eshopspring.dto.response.RespProduct;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public Response<List<RespProduct>> getAllProducts() {
        return productService.getAllProduct();
    }
}
