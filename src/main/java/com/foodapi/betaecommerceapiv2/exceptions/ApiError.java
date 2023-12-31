package com.foodapi.betaecommerceapiv2.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(name = "foodapi", description = "Ecommerce Food API")
public class ApiError {

    @Schema(example = "")
    private String errorMessage;
    private String errorCode;
    private String request;
    private String requestType;
    private String customMessage;
}
