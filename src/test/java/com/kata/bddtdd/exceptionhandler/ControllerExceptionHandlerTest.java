package com.kata.bddtdd.exceptionhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    void setUp() {
        controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    void handleHttpClientErrorException_shouldReturn404AndErrorMessage_whenHttpClientErrorExceptionWith404Occurs() {
        HttpClientErrorException httpClientErrorException =
                new HttpClientErrorException(HttpStatus.NOT_FOUND, "no student data found");

        ResponseEntity responseEntity = controllerExceptionHandler.
                handleHttpClientErrorException(httpClientErrorException);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertEquals("no student data found", responseEntity.getBody());
    }

    @Test
    void handleHttpClientErrorException_shouldReturn400AndBadRequestError_whenHttpClientErrorExceptionWith400Occurs() {
        HttpClientErrorException httpClientErrorException =
                new HttpClientErrorException(HttpStatus.BAD_REQUEST, "bad request a name prefix can only contain alphabets");

        ResponseEntity responseEntity = controllerExceptionHandler.
                handleHttpClientErrorException(httpClientErrorException);

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("bad request a name prefix can only contain alphabets", responseEntity.getBody());
    }

}