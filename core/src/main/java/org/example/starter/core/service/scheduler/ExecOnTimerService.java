package org.example.starter.core.service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.starter.core.service.engine.AsyncService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j(topic = "CORE")
@RequiredArgsConstructor
public class ExecOnTimerService {

    private static final String SCAN_INTERVAL = "PT3S";

    private final AsyncService service;

    @Scheduled(fixedDelayString = SCAN_INTERVAL)
    public void execOnTimer() throws InterruptedException {
        LOG.debug("   ... execOnTimer [{}]", Thread.currentThread().getName());
        service.runAsync(UUID.randomUUID().toString());

    }
}
