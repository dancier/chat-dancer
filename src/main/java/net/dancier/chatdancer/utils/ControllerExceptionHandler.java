package net.dancier.chatdancer.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    // 404
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public NotFoundException handleNotFound(NotFoundException ex) {
        log.info("Requested Chat not found");
        return ex;
    }

    // 400
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public BadRequestException handleBadRequest(BadRequestException ex) {
        log.info("Invalid chat type supplied in request");
        return ex;
    }

    // 500
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public InternalServerException handleGeneralError(InternalServerException ex) {
        log.error("An error occurred processing request, message: " + ex.getMessage());
        return ex;
    }


}
