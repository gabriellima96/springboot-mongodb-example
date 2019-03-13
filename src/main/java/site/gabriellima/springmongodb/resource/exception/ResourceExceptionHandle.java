package site.gabriellima.springmongodb.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import site.gabriellima.springmongodb.exception.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@ControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
        int httpStatus = HttpStatus.NOT_FOUND.value();
        StandardError error = new StandardError(System.currentTimeMillis(), httpStatus, "Object not found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(error);
    }

    @Getter
    @AllArgsConstructor
    private static class StandardError implements Serializable {
        private Long timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
    }
}
