package com.github.jthugg.diary.dateway.ping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PingController {

    @GetMapping("/")
    public ResponseEntity<String> pong(@RequestHeader("X-Request-ID") String requestId) {
        log.info("ping! {}", requestId);
        return ResponseEntity.ok("pong");
    }

    @GetMapping("/check")
    public ResponseEntity<String> check(@RequestHeader("X-Request-ID") String requestId) {
        log.info("check! {}", requestId);
        return ResponseEntity.ok("check");
    }

}
