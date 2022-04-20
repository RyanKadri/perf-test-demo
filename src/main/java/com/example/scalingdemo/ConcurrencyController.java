package com.example.scalingdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ConcurrencyController {

    private int count = 0;
    private AtomicInteger countAtomic = new AtomicInteger(0);

    @GetMapping("/reset")
    public String resetCount() {
        this.count = 0;
        return "Reset";
    }

    @GetMapping("/resetAtomic")
    public String resetCountAtomic() {
        this.countAtomic.set(0);
        return "Reset";
    }

    @GetMapping("/counter")
    public Integer getCounter() {
        return this.count;
    }

    @GetMapping("/counterAtomic")
    public Integer getCounterAtomic() {
        return this.countAtomic.get();
    }

    @GetMapping("/incrementSlow")
    public String incrementSlow() throws InterruptedException {
        int origCount = this.count;
        Thread.sleep(20);
        this.count = origCount + 1;
        return "Hello";
    }

    @GetMapping("/incrementFast")
    public String incrementFast() {
        this.count ++;
        return "Fast!!";
    }

    @GetMapping("/incrementAtomic")
    public String incrementAtomic() {
        this.countAtomic.incrementAndGet();
        return "Fast";
    }
}
