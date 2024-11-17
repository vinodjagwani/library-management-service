package my.cld.library.exception.dto;

import my.cld.library.exception.ErrorPrinter;
import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum implements ErrorPrinter {

    DATA_NOT_FOUND(HttpStatus.BAD_REQUEST),
    INVALID_PARAM(HttpStatus.BAD_REQUEST);

    ErrorCodeEnum(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    private final HttpStatus httpStatus;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
