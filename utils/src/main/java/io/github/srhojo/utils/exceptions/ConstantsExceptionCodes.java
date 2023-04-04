package io.github.srhojo.utils.exceptions;

import org.springframework.http.HttpStatus;

public class ConstantsExceptionCodes {
    /**
     * Generic Exceptions
     */
    public static final String ERRORS_GENERIC_CODE = "001";
    public static final String ERRORS_GENERIC_MESSAGE = "An error has occurred [message=%s].";
    public static final HttpStatus ERRORS_GENERIC_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;


    public static final String ERRORS_VALIDATION_CODE = "002";
    public static final String ERRORS_VALIDATION_MESSAGE = "A validation error has occurred [message=%s].";
    public static final HttpStatus ERRORS_VALIDATION_STATUS = HttpStatus.BAD_REQUEST;


    /**
     * Logic Exceptions
     */
    public static final String ERRORS_PRICE_OUT_OF_DATA_RANGE_CODE = "101";
    public static final String ERRORS_PRICE_OUT_OF_DATA_RANGE_MESSAGE = "Price are out of range for [BrandId: %s, ProductId: %s, ApplicationDateTime: %s]";
    public static final HttpStatus ERRORS_PRICE_OUT_OF_DATE_RANGE_STATUS = HttpStatus.NOT_FOUND;


    public static final String ERRORS_PRICE_NOT_FOUND_CODE = "102";
    public static final String ERRORS_PRICE_NOT_FOUND_MESSAGE = "Price not found [BrandId: %s, ProductId: %s]";
    public static final HttpStatus ERRORS_PRICE_NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;
}
