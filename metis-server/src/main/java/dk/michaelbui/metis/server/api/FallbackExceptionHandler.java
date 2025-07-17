package dk.michaelbui.metis.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FallbackExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(FallbackExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public void handleException(RuntimeException ex) {
        LOGGER.error(ex.getMessage(), ex);
    }
}
