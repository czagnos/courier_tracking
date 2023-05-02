package com.example.courier_tracking.exception;

import com.courier.swaggergen.model.GetNotFoundResponse;
import com.example.courier_tracking.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GetNotFoundResponse handleApiException(final NotFoundException exception) {
        final GetNotFoundResponse apiErrorResponse = new GetNotFoundResponse();

        apiErrorResponse.setDescription(exception.getDescription());

        return apiErrorResponse;
    }
}
