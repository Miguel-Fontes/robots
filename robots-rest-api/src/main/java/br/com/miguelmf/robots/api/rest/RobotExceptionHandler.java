package br.com.miguelmf.robots.api.rest;

import br.com.miguelmf.robots.port.nasa.exception.IllegalCommandException;
import br.com.miguelmf.robots.port.nasa.exception.IllegalPositionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * RobotExceptionHandler contains methods the Handle exceptions while execution operations on
 * Robots
 *
 * @author Miguel Fontes
 */
@ControllerAdvice
public class RobotExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * handleIllegalOperation handles IllegalPositionException and IllegalCommandException, building
     * a message that contains the https tatus code BAD_REQUEST and a message with the details
     * of the error.
     *
     * @param ex the exception
     * @param request the http request
     * @return a Message with a http status 400 (BAD REQUEST) and a message with the details of the failure
     */
    @ExceptionHandler(value = { IllegalPositionException.class, IllegalCommandException.class })
    protected ResponseEntity<Object> handleIllegalOperation(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
