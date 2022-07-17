package org.example.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = "org.example.starter")
@ImportResource("classpath:app-config.xml")
public class RunApp {

    public static final int BUFFER_CAPACITY = 2048;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RunApp.class);
        if (isDefinedStartupProperty()) {
            app.setApplicationStartup(new BufferingApplicationStartup(BUFFER_CAPACITY));
        }
        app.run(args);
    }

    private static boolean isDefinedStartupProperty() {
        return true;
    }

}
