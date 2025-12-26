package az.orient.eshopspring.dto.request;

import lombok.Data;

@Data
public class  ReqUsers {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;

}
