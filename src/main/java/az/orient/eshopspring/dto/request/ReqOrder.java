package az.orient.eshopspring.dto.request;

public record  ReqOrder(Long cartId, Long addressId, Integer quantity ) {
}
