package nl.nightcrawler.spring.kafkastandalone;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.extern.slf4j.Slf4j;
import nl.nightcrawler.spring.kafkastandalone.avro.model.Customer;
import nl.nightcrawler.spring.kafkastandalone.avro.model.UUID;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.SendResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.apache.kafka.clients.consumer.ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class KafkaStandaloneApplicationSpringKafkaTests {

    private static final String SCHEMA_REGISTRY = "http://localhost:20001";
    private static final String BROKER_URL = "http://localhost:20000";
    private static final String DEFAULT_TOPIC = "ota-exp-customer";

    private static final BlockingQueue<Customer> event = new LinkedBlockingDeque<>();

    @BeforeAll
    static void init() {
        startConsumerToTopic1();
    }


    @Test
    void testProduceAndConsume() throws InterruptedException {
        this.sendMessage(new Customer("customerName", java.util.UUID.randomUUID().toString(), 111));
        var eventData = event.poll(10, TimeUnit.SECONDS);
        Objects.requireNonNull(eventData,"Must have customer data");
        assertEquals("customerName",eventData.getCustomerName().toString());
    }


    public KafkaTemplate<UUID, Customer> kafkaTemplateWithAvro() {
        return new KafkaTemplate<>(producerFactoryWithAvro());
    }

    private ProducerFactory<UUID, Customer> producerFactoryWithAvro() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_URL);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put(CLIENT_ID_CONFIG, "nl.nightcrawler.spring.kafkastandalone");
        configProps.put("schema.registry.url", SCHEMA_REGISTRY);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    public void sendMessage(Customer message) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CompletableFuture<SendResult<UUID, Customer>> future = kafkaTemplateWithAvro().send(
                new ProducerRecord<>(DEFAULT_TOPIC,
                        UUID.newBuilder().setUniqueId(java.util.UUID.randomUUID().toString()).build(), message));
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
                countDownLatch.countDown();
            } else {
                log.info("Unable to send message=[{}] due to : ", message, ex);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    private static void startConsumerToTopic1() {
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_URL);
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "nl.nightcrawler.spring.kafkastandalone");
        consumerConfig.put(CLIENT_ID_CONFIG, "nl.nightcrawler.spring.kafkastandalone");
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        consumerConfig.put("schema.registry.url", SCHEMA_REGISTRY);
        consumerConfig.put(ALLOW_AUTO_CREATE_TOPICS_CONFIG, false);
        consumerConfig.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        DefaultKafkaConsumerFactory<UUID, Customer> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumerConfig);

        var containerProperties = new ContainerProperties(DEFAULT_TOPIC);
        containerProperties.setMessageListener(new customerMessageListener());
        var container = new ConcurrentMessageListenerContainer<>(kafkaConsumerFactory, containerProperties);
        container.start();
    }


    @Slf4j
    public static class customerMessageListener implements MessageListener<UUID, Customer> {
        @Override
        public void onMessage(ConsumerRecord<UUID, Customer> data) {
            log.info("########################## New Consuming Message From Message Listener ##########################");
            log.info("Message # {}", data.value());
            log.info("#################################################################################################");
            event.offer(data.value());
        }
    }
}
