package az.turing.userappinspringboot.exception;

import az.turing.userappinspringboot.model.constant.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> notFoundExceptionHandler(NotFoundException ex) {
        exceptionLog(ex);
        return ResponseEntity.status(NOT_FOUND).body(GlobalErrorResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(ErrorCode.NOT_FOUND)
                .errorMessage(ex.getMessage())
                .timeStump(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<GlobalErrorResponse> alreadyExistsExceptionHandler(AlreadyExistsException ex) {
        exceptionLog(ex);
        return ResponseEntity.status(BAD_REQUEST).body(GlobalErrorResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(ErrorCode.ALREADY_EXISTS)
                .errorMessage(ex.getMessage())
                .timeStump(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<GlobalErrorResponse> invalidExceptionHandler(InvalidException ex) {
        exceptionLog(ex);
        return ResponseEntity.status(BAD_REQUEST).body(GlobalErrorResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(ErrorCode.INVALID_INPUT)
                .errorMessage(ex.getMessage())
                .timeStump(LocalDateTime.now())
                .build());
    }

    private void exceptionLog(Exception ex) {
        log.error("{} happened {} ", ex.getClass().getSimpleName(), ex.getMessage());
    }
}
