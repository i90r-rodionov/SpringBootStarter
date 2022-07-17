package org.example.starter.domain.config;

import org.example.starter.domain.repository.RepositoryScanMarker;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("org.example.starter.domain.entity")
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = RepositoryScanMarker.class)
@ComponentScan({"org.example.starter.domain"})
public class DomainConfig {
}
