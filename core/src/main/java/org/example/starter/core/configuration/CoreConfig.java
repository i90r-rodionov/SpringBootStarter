package org.example.starter.core.configuration;

import org.example.starter.core.CoreScanMarker;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = CoreScanMarker.class)
@EnableAspectJAutoProxy
@EnableAsync
public class CoreConfig {
}
