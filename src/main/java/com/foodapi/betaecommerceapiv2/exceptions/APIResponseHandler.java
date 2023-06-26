package com.foodapi.betaecommerceapiv2.exceptions;

import com.foodapi.betaecommerceapiv2.exceptions.cart.CartNotFoundException;
import com.foodapi.betaecommerceapiv2.exceptions.common.BadRequestException;
import com.foodapi.betaecommerceapiv2.exceptions.common.ForbiddenException;
import com.foodapi.betaecommerceapiv2.exceptions.customer.InvalidCredentialsException;
import com.foodapi.betaecommerceapiv2.exceptions.customer.UserExistsException;
import com.foodapi.betaecommerceapiv2.exceptions.customer.UserNotFoundException;
import com.foodapi.betaecommerceapiv2.exceptions.order.OrderNotFoundException;
import com.foodapi.betaecommerceapiv2.exceptions.product.InvalidFilterException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductExistsException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class APIResponseHandler {

    // Common API Exceptions
    // To Handle Internal Server Exception...
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> handleException(Exception ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("Internal Server Error")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // To handle bad request exception
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.BAD_REQUEST.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("Bad Request")
                .build(), HttpStatus.BAD_REQUEST);
    }

    // To handle forbidden exception
    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<ApiError> handleForbiddenException(ForbiddenException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.FORBIDDEN.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("Forbidden")
                .build(), HttpStatus.FORBIDDEN);
    }

    // To handle invalid credentials exception
    @ExceptionHandler({InvalidCredentialsException.class})
    public ResponseEntity<ApiError> handleInvalidUserCredentialsException(InvalidCredentialsException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.BAD_REQUEST.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("Invalid Credentials")
                .build(), HttpStatus.BAD_REQUEST);
    }

    // To handle user already exists exception
    @ExceptionHandler({UserExistsException.class})
    public ResponseEntity<ApiError> handleUserExistsException(UserExistsException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.BAD_REQUEST.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("User Already Exists")
                .build(), HttpStatus.BAD_REQUEST);
    }

    // To handle User Not Found exception
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.NOT_FOUND.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("User Not Found")
                .build(), HttpStatus.NOT_FOUND);
    }

    // Product Exceptions
    // To handle invalid filter exception
    @ExceptionHandler({InvalidFilterException.class})
    public ResponseEntity<ApiError> handleInvalidFilterException(InvalidFilterException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.BAD_REQUEST.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("Invalid Filter")
                .build(), HttpStatus.BAD_REQUEST);
    }

    // To handle Product Already Exists exception
    @ExceptionHandler({ProductExistsException.class})
    public ResponseEntity<ApiError> handleProductExistsException(ProductExistsException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.BAD_REQUEST.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("Product Already Exists")
                .build(), HttpStatus.BAD_REQUEST);
    }

    // To handle Product Not Found exception
    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ApiError> handleProductNotFoundException(ProductNotFoundException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.NOT_FOUND.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("Product Not Found")
                .build(), HttpStatus.NOT_FOUND);
    }

    //order
    // To handle Order Not Found exception
    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<ApiError> handleOrderNotFoundException(OrderNotFoundException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.NOT_FOUND.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("Order Not Found")
                .build(), HttpStatus.NOT_FOUND);
    }

    //Cart Exception
    // To handle Cart Not Found exception
    @ExceptionHandler({CartNotFoundException.class})
    public ResponseEntity<ApiError> handleCartNotFoundException(CartNotFoundException ex, HttpServletRequest request){
        log.error("Exception: "+ ex.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(ApiError.builder()
                .errorMessage(ex.getLocalizedMessage())
                .errorCode(HttpStatus.NOT_FOUND.toString())
                .request(request.getRequestURI())
                .requestType(request.getMethod())
                .customMessage("Cart Not Found")
                .build(), HttpStatus.NOT_FOUND);
    }
}