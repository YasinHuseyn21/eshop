package az.orient.eshopspring.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespUsers {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;

}
