package nl.nightcrawler.spring.kafkastandalone;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KafkaStandaloneApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(KafkaStandaloneApplication.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .web(WebApplicationType.SERVLET)
                .headless(true)
                .registerShutdownHook(true)
                .properties()
                .run(args);
    }


}
