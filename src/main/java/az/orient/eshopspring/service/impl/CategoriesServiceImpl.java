package az.orient.eshopspring.service.impl;

import az.orient.eshopspring.dto.response.RespCategories;
import az.orient.eshopspring.entity.Categories;
import az.orient.eshopspring.service.CategoriesService;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    public static RespCategories convert(Categories categories){
        return RespCategories.builder()
                .id(categories.getId())
                .name(categories.getName()).build();
    }

}
