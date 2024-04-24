package nl.nightcrawler.spring.kafkastandalone;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.extern.slf4j.Slf4j;
import nl.nightcrawler.spring.kafkastandalone.avro.model.Customer;
import nl.nightcrawler.spring.kafkastandalone.avro.model.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.newBuilder;
import static org.apache.kafka.clients.consumer.ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG;


@Slf4j
@SpringBootTest
class KafkaStandaloneApplicationKafkaNativeTests {

    private static final String SCHEMA_REGISTRY = "http://localhost:20001";
    private static final String BROKER_URL = "http://localhost:20000";
    private static final String DEFAULT_TOPIC = "ota-exp-customer";


    @Test
    void testProduceAndConsume() {
        var recordValue = getValue();
        try (Producer<UUID, Customer> producer = new KafkaProducer<>(producerFactoryWithAvro())) {
            log.info("Producer instantiated successfully.");
            var countDownLatch = new CountDownLatch(1);
            try {

                final ProducerRecord<UUID, Customer> record = new ProducerRecord<>(DEFAULT_TOPIC, randomKey(), recordValue);
                producer.send(record, (metadata, e) -> {
                    if (e != null) {
                        log.info("Send failed for record {}", record, e);
                    } else {
                        log.info("Successfully produced message to topic {} partition {} offset {}", metadata.topic(), metadata.partition(), metadata.offset());
                    }
                    countDownLatch.countDown();
                });
                boolean s = countDownLatch.await(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                countDownLatch.countDown();
            }
        }
        consumer(recordValue);
    }

    private static Customer getValue() {
        return new Customer("customerName".concat(RandomStringUtils.randomAlphabetic(5)), "my address", 100);
    }

    private static UUID randomKey() {
        return newBuilder().setUniqueId(java.util.UUID.randomUUID().toString()).build();
    }

    private void consumer(Customer assertValue) {
        try (Consumer<UUID, Customer> consumer = new KafkaConsumer<>(consumerFactoryWithAvro())) {
            log.info("Consumer instantiated");
            consumer.subscribe(Collections.singleton(DEFAULT_TOPIC));
            log.info("Consuming poll #");
            ConsumerRecords<UUID, Customer> consumerRecords = consumer.poll(Duration.ofSeconds(2));
            assert consumerRecords != null;
            for (ConsumerRecord<UUID, Customer> record : consumerRecords) {
                log.info("Consumed message: topic={}; offset={}; key={}; value={}", record.topic(), record.offset(), record.key().getUniqueId(), record.value());
                assert !assertValue.getCustomerName().equals(record.value().getCustomerName());
            }
            consumer.commitAsync((offsets, e) -> {
                if (e != null)
                    log.info("Commit failed for offsets {}", offsets, e);
                else
                    log.info("Commit successful for offsets {}", offsets, e);
            });
            log.info("Closing the consumer.");
        }
        log.info("Consumer closed.");
    }


    private Map<String, Object> producerFactoryWithAvro() {
        Map<String, Object> producerConfig = new HashMap<>();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_URL);
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        producerConfig.put(CLIENT_ID_CONFIG, "nl.nightcrawler.spring.kafkastandalone.customer");
        producerConfig.put("schema.registry.url", SCHEMA_REGISTRY);
        producerConfig.put("acks", "all");
        return producerConfig;
    }

    private Map<String, Object> consumerFactoryWithAvro() {
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_URL);
        consumerConfig.put(CLIENT_ID_CONFIG, "nl.nightcrawler.spring.kafkastandalone.customer");
        consumerConfig.put(GROUP_ID_CONFIG, "nl.nightcrawler.spring.kafkastandalone.customer");
        consumerConfig.put("schema.registry.url", SCHEMA_REGISTRY);
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        consumerConfig.put(ALLOW_AUTO_CREATE_TOPICS_CONFIG, false);
        consumerConfig.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        return consumerConfig;
    }


}
