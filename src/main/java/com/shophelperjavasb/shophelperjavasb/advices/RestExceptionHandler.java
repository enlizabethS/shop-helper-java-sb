package com.shophelperjavasb.shophelperjavasb.advices;

import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.exceptions.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFound(NotFoundException exception) {
        log.error(exception.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ExceptionDto.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build());
    }
}
