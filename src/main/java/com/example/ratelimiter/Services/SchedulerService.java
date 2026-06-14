package com.example.ratelimiter.Services;

import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.beans.factory.annotation.Value;
import java.time.Instant;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.time.Duration;

@Service
public class SchedulerService 
{
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private RateLimiterService rateLimiterService;

    @Value("${app.user.active.duration}")
    private Duration activeDuration;

    ScheduledFuture<?> cleanup;

    @PostConstruct
    public void init()
    {
        startJob();
    }

    private void startJob()
    {
        System.out.println("Job Started");
        cleanup = taskScheduler.scheduleWithFixedDelay(() -> {
            System.out.println("Scheduler started");
            Instant threshold = Instant.now().minus(activeDuration);
            rateLimiterService.cleanupInactiveUsers(threshold);}, Duration.ofMinutes(2));
    }

    private void stopJob()
    {
        cleanup.cancel(true);
    }

    @PreDestroy
    public void destroy()
    {
        System.out.println("Scheduler stopped");
        stopJob();
    }
}
