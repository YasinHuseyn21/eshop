package az.orient.eshopspring.dto.response;
import az.orient.eshopspring.entity.Users;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RespAddress {

    private Long id;
    private RespUsers respUsers;
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;

}
