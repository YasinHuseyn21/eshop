package az.orient.eshopspring.service;

import az.orient.eshopspring.dto.request.ReqCategories;
import az.orient.eshopspring.dto.response.RespCategories;
import az.orient.eshopspring.dto.response.Response;

import java.util.List;

public interface CategoriesService {

    Response<List<RespCategories>> getAllCategories();

    Response<RespCategories> getCategoryById(Integer id);

    Response createCategory(ReqCategories reqCategories);

    Response deleteCategoryById(Integer id);

}
