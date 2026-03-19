package az.orient.eshopspring.service.impl;

import az.orient.eshopspring.dto.request.ReqCategories;
import az.orient.eshopspring.dto.response.RespCategories;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.dto.response.ResponseStatus;
import az.orient.eshopspring.entity.Categories;
import az.orient.eshopspring.enums.EnumStatus;
import az.orient.eshopspring.exception.ExceptionConst;
import az.orient.eshopspring.exception.ShopException;
import az.orient.eshopspring.repo.DataCategoriesRepo;
import az.orient.eshopspring.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final DataCategoriesRepo dataCategoriesRepo;


    @Override
    public Response<List<RespCategories>> getAllCategories() {
        Response<List<RespCategories>> response = new Response<>();
        List<RespCategories> respCategoriesList = new ArrayList<>();
        try {
            List<Categories> listCategories = dataCategoriesRepo.findCategoriesByActive(EnumStatus.ACTIVE.getValue());
            if (listCategories == null) {
                throw new ShopException(ExceptionConst.CATEGORIES_NOT_FOUND, "Category not found");
            }
            for (Categories category : listCategories) {
                RespCategories respCategories = convert(category);
                respCategoriesList.add(respCategories);
            }
            response.setT(respCategoriesList);
            response.setStatus(ResponseStatus.success());


        } catch (ShopException ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, ex.getMessage()));
        }
        return response;
    }

    @Override
    public Response<RespCategories> getCategoryById(Integer id) {
        Response<RespCategories> response = new Response<>();
        try {
            Categories categories = dataCategoriesRepo.findCategoriesById(id);
            if (categories == null) {
                throw new ShopException(ExceptionConst.CATEGORIES_NOT_FOUND, "Category not found");
            }
            RespCategories respCategories = convert(categories);
            response.setT(respCategories);
            response.setStatus(ResponseStatus.success());
        } catch (ShopException ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception e) {
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, e.getMessage()));
        }
        return response;
    }

    @Override
    public Response createCategory(ReqCategories reqCategories) {
        Response response = new Response<>();
        try {
            if (reqCategories == null) {
                throw new ShopException(ExceptionConst.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Categories categories = dataCategoriesRepo.findCategoriesByName(reqCategories.getCategoryName());
            if (categories != null) {
                throw new ShopException(ExceptionConst.CATEGORY_ALREADY_EXIST, "Category already exist");
            }
            Categories categories1 = Categories.builder()
                    .name(reqCategories.getCategoryName())
                    .build();
            dataCategoriesRepo.save(categories1);
            response.setT(convert(categories1));

            response.setStatus(ResponseStatus.success());


        } catch (ShopException e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, ex.getMessage()));
        }
        return response;
    }

    @Override
    public Response deleteCategoryById(Integer id) {
        Response response = new Response();
        try {
            if (id == null) {
                throw new ShopException(ExceptionConst.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Categories categories = dataCategoriesRepo.findCategoriesById(id);
            if (categories == null) {
                throw new ShopException(ExceptionConst.CATEGORIES_NOT_FOUND, "Category not found");
            }
            categories.setActive(EnumStatus.DEACTIV.getValue());
            dataCategoriesRepo.save(categories);
            response.setStatus(ResponseStatus.success());

        } catch (ShopException ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, ex.getMessage()));
        }

        return response;
    }


    public static RespCategories convert(Categories categories) {
        return RespCategories.builder()
                .id(categories.getId())
                .name(categories.getName()).build();
    }


}
