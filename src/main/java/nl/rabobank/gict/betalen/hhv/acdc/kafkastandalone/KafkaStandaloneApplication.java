package nl.rabobank.gict.betalen.hhv.acdc.kafkastandalone;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.metrics.ApplicationStartup;

@SpringBootApplication
public class KafkaStandaloneApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(KafkaStandaloneApplication.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .web(WebApplicationType.SERVLET)
                .headless(true)
                .registerShutdownHook(true)
                .run(args);
    }


}
