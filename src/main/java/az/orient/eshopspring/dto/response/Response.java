package az.orient.eshopspring.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response<T> {

    @JsonProperty("response")
    private T t;
    private ResponseStatus status;

}
