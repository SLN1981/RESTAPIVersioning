package com.example.restapiversioning.controller;

import com.example.restapiversioning.versioning.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @ApiVersion(1)
    @GetMapping("/hello")
    public String helloV1() {
        return "Hello, World! (v1)";
    }

    @ApiVersion(2)
    @GetMapping("/hello")
    public String helloV2() {
        return "Hello, World! (v2) - Enhanced Version";
    }
}
