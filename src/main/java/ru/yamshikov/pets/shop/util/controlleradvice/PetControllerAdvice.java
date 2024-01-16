package ru.yamshikov.pets.shop.util.controlleradvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.yamshikov.pets.shop.model.ErrorResponse;
import ru.yamshikov.pets.shop.util.errors.PetValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class PetControllerAdvice {

    private static String formatInstantNowIntoString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

    @ExceptionHandler(PetValidationException.class)
    public ResponseEntity<Object> handleException(PetValidationException exception) {
        ErrorResponse response = new ErrorResponse(
                "Неверно введены данные в JSON'e",
                exception.getErrors(),
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleException(NoSuchElementException exception) {
        ErrorResponse response = new ErrorResponse(
                "Животного с таким UUID не найдено",
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleException(MethodArgumentTypeMismatchException exception) {
        ErrorResponse response = new ErrorResponse(
                "Неверный формат UUID животного",
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        ErrorResponse response = new ErrorResponse(
                "Неизвестная ошибка",
                List.of(exception.getLocalizedMessage()),
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException exception) {
        ErrorResponse response = new ErrorResponse(
                "Неверный формат принятого JSON'a",
                List.of(exception.getLocalizedMessage()),
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
