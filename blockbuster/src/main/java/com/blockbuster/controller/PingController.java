package com.blockbuster.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/api/ping")
    public String publicPing() {
        return "pong (public, no auth needed)";
    }

    @GetMapping("/api/secure-ping")
    public String securePing() {
        return "pong (secure, auth needed)";
    }
}
