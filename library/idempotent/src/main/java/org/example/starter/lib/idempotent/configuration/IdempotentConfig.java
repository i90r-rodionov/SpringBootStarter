package org.example.starter.lib.idempotent.configuration;

import org.example.starter.lib.idempotent.IdempotentScanMarker;
import org.example.starter.lib.idempotent.domain.repository.IdempotentRepositoryScanMarker;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = IdempotentScanMarker.class)
@EnableAspectJAutoProxy
public class IdempotentConfig {
}
