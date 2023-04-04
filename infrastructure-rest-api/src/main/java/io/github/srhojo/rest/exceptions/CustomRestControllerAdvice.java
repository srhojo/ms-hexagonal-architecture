package io.github.srhojo.rest.exceptions;

import io.github.srhojo.utils.exceptions.CustomException;
import io.github.srhojo.utils.exceptions.CustomExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static io.github.srhojo.utils.exceptions.ConstantsExceptionCodes.*;


@RestControllerAdvice
public class CustomRestControllerAdvice extends ResponseEntityExceptionHandler {
    /**
     * Method to response handled exceptions
     *
     * @param customException the handled warehouse exception
     * @return ResponseEntity<WarehouseExceptionResponse>
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> handleWarehouseException(final CustomException customException) {
        final CustomExceptionResponse response = CustomExceptionResponse.of(customException.getStatus(),
                customException.getCode(), customException.getDetails());
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * Method to response unhandled exceptions
     *
     * @param runtimeException the generic runtime exception
     * @return ResponseEntity<WarehouseExceptionResponse>
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomExceptionResponse> handleRuntimeException(final RuntimeException runtimeException) {
        final CustomExceptionResponse wer = CustomExceptionResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,
                ERRORS_GENERIC_CODE, String.format(ERRORS_GENERIC_MESSAGE, runtimeException.getMessage()));
        return new ResponseEntity<>(wer, wer.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final CustomExceptionResponse wer = CustomExceptionResponse.of(HttpStatus.BAD_REQUEST, ERRORS_VALIDATION_CODE,
                String.format(ERRORS_VALIDATION_MESSAGE, ex.getMessage()));
        return new ResponseEntity<>(wer, wer.getStatus());
    }

}
