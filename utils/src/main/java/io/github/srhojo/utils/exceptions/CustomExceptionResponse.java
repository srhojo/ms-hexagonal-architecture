package io.github.srhojo.utils.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class CustomExceptionResponse {
    @Schema(description = "HttpStatus.", example = "401, 500,...")
    private final HttpStatus status;

    @Schema(description = "Unique identification code", example = "001,010,...")
    private final String code;

    @Schema(description = "Generic java object who contains extra exception information.")
    private final Object details;

    private final LocalDateTime timestamp;

    private CustomExceptionResponse(final HttpStatus status, final String code, final Object details) {
        this.status = status;
        this.code = code;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    @JsonCreator
    private CustomExceptionResponse(@JsonProperty("status") final HttpStatus status, @JsonProperty("code") final String code, @JsonProperty("details") final Object details, @JsonProperty("timestamp") LocalDateTime timestamp) {
        this.status = status;
        this.code = code;
        this.details = details;
        this.timestamp = timestamp;
    }

    public static CustomExceptionResponse of(final HttpStatus status, final String code, final Object details) {
        return new CustomExceptionResponse(status, code, details);
    }
}
