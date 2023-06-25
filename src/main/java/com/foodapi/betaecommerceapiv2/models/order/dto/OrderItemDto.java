package com.foodapi.betaecommerceapiv2.models.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    //calling these methods instead of calling the whole order model
    private  Double price;

    private  int quantity;

    private  Long OrderId;

    private Long productid;

}
