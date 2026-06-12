package com.example.ratelimiter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratelimiter.Services.RateLimiterService;

@RestController
public class RequestController 
{
    @Autowired
    RateLimiterService rateLimiterService;

    @PostMapping("/registerRequest")
    public ResponseEntity<String> registerRequest(@RequestParam("ip") String ipAddress)
    {
        if(rateLimiterService.registerRequest(ipAddress))
        {
            return new ResponseEntity<>("Accepted", HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rejected");
        
    }
}
