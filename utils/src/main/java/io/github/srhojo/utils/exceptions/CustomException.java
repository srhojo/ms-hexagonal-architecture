package io.github.srhojo.utils.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Schema(name = "CustomException", description = "Custom runtime exception")
public class CustomException extends RuntimeException {

    @Schema(name = "status", example = "401,500, ...", description = "Http Status")
    private final HttpStatus status;

    @Schema(name = "code", description = "Unique identification code", example = "001,010,...")
    private final String code;

    @Schema(name = "details", description = "Generic java object who contains extra exception information.")
    private final transient Object details;

    public CustomException(final Object details) {
        this.status = ConstantsExceptionCodes.ERRORS_GENERIC_STATUS;
        this.code = ConstantsExceptionCodes.ERRORS_GENERIC_CODE;
        this.details = details;
    }

    @JsonCreator
    public CustomException(@JsonProperty("status") final HttpStatus status, @JsonProperty("code") final String code, @JsonProperty("details") final Object details) {
        this.status = status;
        this.code = code;
        this.details = details;
    }
}
