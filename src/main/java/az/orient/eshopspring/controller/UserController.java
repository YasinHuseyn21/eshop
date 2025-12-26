package az.orient.eshopspring.controller;

import az.orient.eshopspring.dto.request.ReqUsers;
import az.orient.eshopspring.dto.response.RespUsers;
import az.orient.eshopspring.dto.response.Response;
import az.orient.eshopspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    Response<List<RespUsers>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/ById/{id}")
    public Response<RespUsers> getUserById(@PathVariable("id") Long id) {
        return userService.getAllUsersByIdAndActive(id);
    }

    @PostMapping("/create")
    public Response<RespUsers> create(@RequestBody ReqUsers reqUser) {
        return userService.createUsers(reqUser);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return userService.deleteUsers(id);
    }

}
