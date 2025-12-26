package az.orient.eshopspring.dto.request;

import az.orient.eshopspring.dto.response.RespUsers;
import lombok.Data;

@Data
public class ReqAddress {
    private Long id;
    private RespUsers respUsers;
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;
    private Long usersId;
}
