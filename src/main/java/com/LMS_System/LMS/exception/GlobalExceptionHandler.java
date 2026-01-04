package com.LMS_System.LMS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictHandling.class)
    public ResponseEntity<Map<String, String>> handleConflict(
            ConflictHandling e) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(
            Exception e) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Map<String, String>> handleNotFound(
            NotFound e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(GateWayTimeOut.class)
    public ResponseEntity<Map<String, String>> handleGateWayTimeOut(
            GateWayTimeOut e) {

        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(NotAcceptable.class)
    public ResponseEntity<Map<String, String>> handelNotAcceptable(
            NotAcceptable e) {

        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(Map.of("message", e.getMessage()));
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handelBadRequest(
            BadRequestException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(UnAuthorized.class)
    public ResponseEntity<Map<String, String>> handelUnAuthorized(
            UnAuthorized e) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(ForBidden.class)
    public ResponseEntity<Map<String, String>> handelForBidden(
            ForBidden e) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", e.getMessage()));
    }

}
