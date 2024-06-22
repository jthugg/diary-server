package com.github.jthugg.diary.external.oauth.github.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/oauth/callback/github/test")
    public String one() {
        return "callback";
    }

}
