package com.ideas2it.fooddeliverymanagement.exception;

import com.ideas2it.fooddeliverymanagement.dto.ErrorResponseDTO;
import com.ideas2it.fooddeliverymanagement.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * <p>
 * Handles the exception thrown by Food Delivery Management Application
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 14-DEC-2022
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * <p>
     * To handle the FoodDeliveryManagementException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status and code
     */
    @ExceptionHandler(FoodDeliveryManagementException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeManagementException(FoodDeliveryManagementException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatus(),exception.getMessage());
        return new ResponseEntity<> (errorResponse, exception.getStatus());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponseDTO noSuchElementException(NoSuchElementException exception) {
        return new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(),
               exception.getMessage());

    }
}
