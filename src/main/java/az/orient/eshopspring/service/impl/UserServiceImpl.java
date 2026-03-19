package az.orient.eshopspring.service.impl;

import az.orient.eshopspring.dto.request.ReqUsers;
import az.orient.eshopspring.dto.response.RespUsers;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.dto.response.ResponseStatus;
import az.orient.eshopspring.entity.Users;
import az.orient.eshopspring.enums.EnumStatus;
import az.orient.eshopspring.exception.ExceptionConst;
import az.orient.eshopspring.exception.ShopException;
import az.orient.eshopspring.repo.DataUserRepo;
import az.orient.eshopspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final DataUserRepo dataUserRepo;

    @Override
    public Response<List<RespUsers>> getAllUsers() {
        Response<List<RespUsers>> response = new Response<>();
        try {
            List<Users> userList = dataUserRepo.findUsersByActive(EnumStatus.ACTIVE.getValue());
            List<RespUsers> respUsers = userList.stream().map(this::fill).toList();
            if (respUsers.isEmpty()) {
                throw new ShopException(ExceptionConst.USER_NOT_FOUND, "User not found");
            }
            response.setT(respUsers);
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
    public Response<RespUsers> getAllUsersByIdAndActive(Long id) {
        Response<RespUsers> response = new Response<>();
        try {
            if (id == null) {
                throw new ShopException(ExceptionConst.INVALID_PARAM, "Invalid Parameter");
            }
            Users userList = dataUserRepo.findUsersByIdAndActive(id, EnumStatus.ACTIVE.getValue());
            if (userList == null) {
                throw new ShopException(ExceptionConst.USER_NOT_FOUND, "User not found");
            }
            RespUsers respUsers = fill(userList);
            response.setT(respUsers);
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
    public Response<RespUsers> createUsers(ReqUsers userReq) {
        Response<RespUsers> response = new Response<>();
        try {
            String email = userReq.getEmail();

            if (email == null || email.isEmpty()) {
                throw new ShopException(ExceptionConst.INVALID_PARAM, "Invalid Parameter");
            }
            Users users = Users.builder()

                    .name(userReq.getName())
                    .surname(userReq.getSurname())
                    .phone(userReq.getPhone())
                    .email(userReq.getEmail()).build();
            dataUserRepo.save(users);
            response.setT(fill(users));
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
    public Response deleteUsers(Long id) {
        Response<RespUsers> response = new Response<>();
        try {
            Users users = dataUserRepo.findUsersByIdAndActive(id, EnumStatus.ACTIVE.getValue());
            if (users == null) {
                throw new ShopException(ExceptionConst.USER_NOT_FOUND, "User not found");
            }
            users.setActive(EnumStatus.DEACTIV.getValue());
            response.setStatus(ResponseStatus.success());
            dataUserRepo.save(users);
        } catch (ShopException e) {
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConst.INTERNAL_EXCEPTION, e.getMessage()));
        }
        return response;
    }


    public RespUsers fill(Users user) {

        return RespUsers.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

}
