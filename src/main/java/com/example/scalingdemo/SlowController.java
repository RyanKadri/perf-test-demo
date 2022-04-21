package com.example.scalingdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlowController {

    @GetMapping("/slow")
    public String slowRequest() throws InterruptedException {
        Thread.sleep(5000);
        return "Sllooooowwww";
    }

    @GetMapping("/fast")
    public String fastRequest() throws InterruptedException {
        Thread.sleep(200);
        return "Fast!";
    }
}
