package az.orient.eshopspring.service;

import az.orient.eshopspring.dto.request.ReqUsers;
import az.orient.eshopspring.dto.response.RespUsers;
import az.orient.eshopspring.dto.response.Response;

import java.util.List;

public interface UserService {

    Response<List<RespUsers>> getAllUsers();

    Response<RespUsers> getAllUsersByIdAndActive(Long id);

    Response<RespUsers> createUsers(ReqUsers user);

    Response deleteUsers(Long id);
}
