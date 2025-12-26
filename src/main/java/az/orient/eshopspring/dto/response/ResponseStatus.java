package az.orient.eshopspring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseStatus {

    private Integer code;
    private String message;

    private static final Integer SUCCESS_CODE = 1;
    private static final String SUCCESS_MESSAGE = "success";

    public static ResponseStatus success() {
        return new ResponseStatus(SUCCESS_CODE, SUCCESS_MESSAGE);
    }
}
