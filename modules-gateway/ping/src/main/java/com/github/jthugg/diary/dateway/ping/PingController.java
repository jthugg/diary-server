package com.github.jthugg.diary.dateway.ping;

import com.github.jthugg.diary.presentation.web.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/")
    public HttpEntity<Response<String>> pong() {
        return Response.ofEntity(HttpStatus.OK, "pong");
    }

    @GetMapping("/check")
    public HttpEntity<Response<String>> check() {
        return Response.ofEntity(HttpStatus.OK, "check");
    }

    @GetMapping({"/public", "/anonymous", "/authenticated"})
    public HttpEntity<Response<String>> publicMethod(HttpServletRequest request) {
        return Response.ofEntity(HttpStatus.OK, request.getRequestURI());
    }

}
