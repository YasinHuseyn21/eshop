package az.orient.eshopspring.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespCategories {
    private Integer id;
    private String name;

}
