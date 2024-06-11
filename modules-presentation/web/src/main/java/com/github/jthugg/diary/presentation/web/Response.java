package com.github.jthugg.diary.presentation.web;

import lombok.Getter;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

@Getter
public class Response<T> {

    private final Instant timestamp;
    private final String requestId;
    private final T content;

    private Response(T content) {
        this.timestamp = Instant.now();
        this.requestId = MDC.get(RequestTracingFilter.REQUEST_ID);
        this.content = content;
    }

    public static <T> ResponseEntity<Response<T>> responseEntityOf(HttpStatus status, T content) {
        return ResponseEntity.status(status).body(new Response<>(content));
    }

    public static <T> ResponseEntity<Response<T>> voidResponseEntityOf(HttpStatus status) {
        return ResponseEntity.status(status).body(new Response<>(null));
    }

}
