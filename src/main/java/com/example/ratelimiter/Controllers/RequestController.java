package com.example.ratelimiter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratelimiter.Services.RateLimiterService;

@RestController
public class RequestController 
{
    
    @Autowired
    RateLimiterService rateLimiterService;

    @GetMapping("/test")
    public ResponseEntity<Integer> getSize()
    {

        return ResponseEntity.status(HttpStatus.OK).body(rateLimiterService.getSize());
        
    }

    @GetMapping("/request")
    public ResponseEntity<String> registerRequest()
    {

        return ResponseEntity.status(HttpStatus.OK).body("Request Received");
        
    }
    
}
