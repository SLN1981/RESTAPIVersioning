package com.example.restapiversioning.controller;

import com.example.restapiversioning.versioning.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiVersion(1)
@RestController
public class HelloUserV1Controller {

    @GetMapping("/greet")
    public String sayHi() {
        return "Hi user! (Basic greeting - v1)";
    }

    @GetMapping("/welcome")
    public String sayHello() {
        return "Hello user! (Simple welcome - v1)";
    }
}
