package com.foodapi.betaecommerceapiv2.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(name = "foodapi", description = "Ecommerce Food API")
public class ProductAPIError {
    @Schema(example = "Product already exists")
    private String errorMessage;
    @Schema(example = "404")
    private String errorCode;
    @Schema(example = "")
    private String request;
    @Schema(example = "")
    private String requestType;
    @Schema(example = "Product already exists")
    private String customMessage;
}
