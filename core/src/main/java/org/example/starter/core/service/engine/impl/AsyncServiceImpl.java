package org.example.starter.core.service.engine.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.starter.core.service.engine.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j(topic = "CORE")
public class AsyncServiceImpl implements AsyncService {

    @Transactional
    @Async
    @Override
    public void runAsync(String arg) throws InterruptedException {
        LOG.debug("   ... runAsync start [{}] arg=[{}]", Thread.currentThread().getName(), arg);
        Thread.sleep(4000);
        LOG.debug("   ... runAsync stop  [{}] arg=[{}]", Thread.currentThread().getName(), arg);
    }
}
