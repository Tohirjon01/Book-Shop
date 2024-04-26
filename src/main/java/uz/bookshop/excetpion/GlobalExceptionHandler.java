package uz.bookshop.excetpion;

import lombok.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ErrorResponse_2 {
        private String message;
    }


    @ExceptionHandler(RoleException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRoleException(RoleException roleException) {
        log.error(roleException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(roleException.getMessage())
                        .build()
                );
    }



    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResponseEntity<Object> handleUserException(UserException userException) {
        log.error(userException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(userException.getMessage())
                        .build()
                );
    }


    @ExceptionHandler(BookException.class)
    @ResponseBody
    public ResponseEntity<Object> handleBookException(BookException bookException) {
        log.error(bookException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(bookException.getMessage())
                        .build()
                );
    }

}
