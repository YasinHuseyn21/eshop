package az.orient.eshopspring.controller;

import az.orient.eshopspring.dto.request.ReqCategories;
import az.orient.eshopspring.dto.response.RespCategories;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;


    @GetMapping("/categoryList")
    public Response<List<RespCategories>> getCategories() {
        return categoriesService.getAllCategories();
    }

    @GetMapping("/listById")
    public Response<RespCategories> getCategoryById(@RequestParam("categoryId") Integer id) {
        return categoriesService.getCategoryById(id);
    }

    @PostMapping("/create")
    public Response createCategory(@RequestBody ReqCategories reqCategories) {
        return categoriesService.createCategory(reqCategories);
    }

    @DeleteMapping("/delete")
    public Response deleteCategory(@RequestParam("categoryId") Integer id) {
        return categoriesService.deleteCategoryById(id);
    }
}
