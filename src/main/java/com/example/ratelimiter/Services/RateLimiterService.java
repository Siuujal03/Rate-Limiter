package com.example.ratelimiter.Services;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.example.ratelimiter.Models.UserRequestInfo;

@Service
public class RateLimiterService 
{

    Map<String, UserRequestInfo> requests;

    @Value("${app.request.limit}")
    private int limit;

    @Value("${app.request.window}")
    private Duration window;

    public RateLimiterService()
    {
        this.requests = new ConcurrentHashMap<>();
    }

    public boolean registerRequest(String ipAddress)
    {
        UserRequestInfo userRequestInfo = requests.computeIfAbsent(ipAddress, key -> new UserRequestInfo());

        Instant currentTime = Instant.now();

        return userRequestInfo.tryAndRequest(currentTime, limit, window);
    }

}
