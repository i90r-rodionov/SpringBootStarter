package org.example.starter.core.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"org.example.starter.core.service.scheduler"})
@EnableScheduling
//@EnableSchedulerLock
@EnableAsync
@ConditionalOnProperty(name="scheduler.enabled", matchIfMissing = true)
public class SchedulerConfig {
}
