package com.example.restapiversioning.controller;

import com.example.restapiversioning.versioning.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiVersion(2)
@RestController
public class HelloUserV2Controller {

    @GetMapping("/greet")
    public String sayHi() {
        return "Hi there, valued user! (Enhanced greeting with improved user experience - v2)";
    }

    @GetMapping("/welcome")
    public String sayHello() {
        return "Hello and welcome, dear user! (Enhanced welcome message with personalized touch - v2)";
    }
}
