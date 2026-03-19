package az.orient.eshopspring.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShopException extends RuntimeException {
    public Integer code;

    public ShopException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
