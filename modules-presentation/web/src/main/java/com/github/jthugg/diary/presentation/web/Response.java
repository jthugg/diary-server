package com.github.jthugg.diary.presentation.web;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

@Getter
public class Response<T> {

    private final Instant timestamp;
    private final T content;

    private Response() {
        this.timestamp = Instant.now();
        this.content = null;
    }

    private Response(T content) {
        this.timestamp = Instant.now();
        this.content = content;
    }

    public static <T> ResponseEntity<Response<T>> ofEntity(HttpStatusCode status) {
        return ResponseEntity.status(status).body(new Response<>());
    }

    public static <T> ResponseEntity<Response<T>> ofEntity(HttpStatusCode status, T content) {
        return ResponseEntity.status(status).body(new Response<>(content));
    }

}
