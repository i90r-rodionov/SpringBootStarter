package org.example.starter.lib.idempotent.configuration;

import org.example.starter.lib.idempotent.domain.repository.IdempotentRepositoryScanMarker;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("org.example.starter.lib.idempotent.domain")
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = IdempotentRepositoryScanMarker.class)
@ComponentScan({"org.example.starter.lib.idempotent.domain"})
public class IdempotentDomainConfig {
}
