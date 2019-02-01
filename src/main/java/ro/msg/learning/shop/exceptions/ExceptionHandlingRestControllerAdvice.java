package ro.msg.learning.shop.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingRestControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Response handleNotFoundException(ResourceNotFoundException ex) {
        return new Response(ex.getMessage());
    }
}
