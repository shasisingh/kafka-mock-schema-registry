package nl.rabobank.gict.betalen.hhv.acdc.kafkastandalone.service;


import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockBroker {

    @Value("${register.kafka_broker_server.port:30003}")
    private int brokerPorts;

    private EmbeddedKafkaHolder embeddedKafkaHolder;

    @PostConstruct
    private void startBroker() {
        var kafkaHolder = EmbeddedKafkaHolder.builder()
                .canUseDefaultTopic(true)
                .numberOfBroker(1)
                .numberOfPartitions(2)
                .kafkaPorts(new int[]{brokerPorts})
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("**** Shutting down EmbeddedKafkaHolder apps ******");
            kafkaHolder.stop();
        }));
        embeddedKafkaHolder = kafkaHolder;
    }

}
