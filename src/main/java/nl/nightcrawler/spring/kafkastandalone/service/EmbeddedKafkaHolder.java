package nl.nightcrawler.spring.kafkastandalone.service;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaZKBroker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import static java.util.Objects.isNull;

@Builder
@Slf4j
public class EmbeddedKafkaHolder {

    @Builder.Default
    private final String[] defaultTopic = {
            "ota-exp-topic1",
            "ota-exp-topic2",
            "ota-exp-topic3",
            "ota-exp-topic4"
    };

    private EmbeddedKafkaBroker embeddedKafka;
    private boolean canUseDefaultTopic;
    @Builder.Default
    private int numberOfBroker = 1;
    @Builder.Default
    private int numberOfPartitions = 2;
    private String[] topics;
    private int[] kafkaPorts;
    @Builder.Default
    private Map<String, String> additionalProperties = new HashMap<>();


    private final Map<String, String> defaultProperties =
            Map.ofEntries(
                    Map.entry("auto.create.topics.enable", "false"),
                    Map.entry("migration.enabled", "false"),
                    Map.entry("auto.leader.rebalance.enable", "true"),
                    Map.entry("unclean.leader.election.enable", "false"),
                    Map.entry("offsets.retention.minutes", "5"),
                    Map.entry("transaction.state.log.replication.factor", "5"),
                    Map.entry("transaction.state.log.min.isr", "1"),
                    Map.entry("controlled.shutdown.enable", "true"));

    public EmbeddedKafkaHolder start() {
        if (this.canUseDefaultTopic) {
            this.topics = defaultTopic;
            log.info("applying default topics: {}", Arrays.stream(defaultTopic).toList());
        }

        if (Objects.isNull(this.additionalProperties)) {
            this.additionalProperties = defaultProperties;
            log.info("applying default defaultProperties: {}", defaultProperties);
        }

        try {
            var kafkaBroker =
                    new EmbeddedKafkaZKBroker(numberOfBroker, true, numberOfPartitions, topics)
                            .kafkaPorts(kafkaPorts)
                            .brokerProperties(this.additionalProperties.isEmpty() ? defaultProperties : additionalProperties)
                            .brokerListProperty("spring.kafka.bootstrap-servers");

            log.info("starting with parameter:{}", this);

            kafkaBroker.afterPropertiesSet();
            log.info("KAFKA BROKER STARTED AND RUNNING ON => {}", kafkaBroker.getBrokersAsString());
            System.setProperty("BROKER_SERVER", kafkaBroker.getBrokersAsString());
            this.embeddedKafka = kafkaBroker;
        } catch (Exception e) {
            throw new KafkaException("Embedded broker failed to start", e);
        }
        return this;
    }

    public void stop() {
        try {
            if (Objects.nonNull(this.embeddedKafka)) {
                embeddedKafka.destroy();
            }
        } catch (Exception e) {
            log.error("Error while stopping resources", e);
        }
    }

    public EmbeddedKafkaBroker instanceOfEmbeddedKafka() {
        if (isNull(embeddedKafka)) {
            throw new KafkaException("Embedded kafka server is not read, Initialized before use.");
        }
        return embeddedKafka;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", EmbeddedKafkaHolder.class.getSimpleName() + "[", "]")
                .add("defaultTopic=" + Arrays.toString(defaultTopic))
                .add("canUseDefaultTopic=" + canUseDefaultTopic)
                .add("numberOfBroker=" + numberOfBroker)
                .add("numberOfPartitions=" + numberOfPartitions)
                .add("topics=" + Arrays.toString(topics))
                .add("kafkaPorts=" + Arrays.toString(kafkaPorts))
                .add("additionalProperties=" + additionalProperties)
                .add("defaultProperties=" + defaultProperties)
                .toString();
    }
}
