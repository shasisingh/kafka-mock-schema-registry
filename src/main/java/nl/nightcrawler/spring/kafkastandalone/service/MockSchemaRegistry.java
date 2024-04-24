package nl.nightcrawler.spring.kafkastandalone.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.confluent.kafka.schemaregistry.client.rest.entities.SchemaString;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.nightcrawler.spring.kafkastandalone.model.Schema;
import nl.nightcrawler.spring.kafkastandalone.model.SchemaDefinition;
import nl.nightcrawler.spring.kafkastandalone.utils.AvroHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


@Getter
@Slf4j
@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockSchemaRegistry {
    private static final String BOOLEAN_PATTERN = "false|true";
    private static final String ALL_SUBJECT_PATTERN = "/subjects";
    private static final AtomicInteger mappedSchemaIndex = new AtomicInteger(1);
    private static final String CONTENT_TYPE = "Content-Type";

    private WireMockServer wireMockServer;

    @Value("${register.schema_registry.port:30001}")
    private int schemaRegistryPort;

    @Value("${register.schema_registry.http_port:30002}")
    private int schemaRegistryHttpPort;


    @PostConstruct
    private void startMockServer() {

        this.wireMockServer = new WireMockServer(
                wireMockConfig()
                        .port(schemaRegistryPort)
                        .httpsPort(schemaRegistryHttpPort)
                        .disableRequestJournal()
        );
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
        log.info("SCHEMA REGISTRY SERVER STARTED AND RUNNING ON PORT =>  {}", wireMockServer.port());
        createDefaultStub();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("**** Shutting down wireMockServer/Schema registry apps ******");
            wireMockServer.stop();
        }));
    }

    public void stop() {
        wireMockServer.stop();
    }


    private void createDefaultStub() {
        schemas().forEach(schema -> {
            try {
                var schemaDefinition = SchemaDefinition.
                        builder()
                        .avroKey(AvroHelper.getSchema(schema.key()).toString())
                        .avroValue(AvroHelper.getSchema(schema.value()).toString())
                        .topicToPublish(schema.topicName())
                        .build();
                log.info("default schema register index :{}", registerNewSchemaPerTopic(schemaDefinition));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

    public List<String> registerNewSchemaPerTopic(final SchemaDefinition schema) throws IOException {

        int schemaIndexId = mappedSchemaIndex.getAndIncrement();

        WireMock.stubFor(postSubjectVersion(getValueSubject(schema.getTopicToPublish()))
                .willReturn(getWithedHeader()
                        .withBody(formatAndGet(schemaIndexId)))
        );

        WireMock.stubFor(postSubjectVersion(getKeySubject(schema.getTopicToPublish()))
                .willReturn(getWithedHeader()
                        .withBody(formatAndGet(schemaIndexId)))
        );

        WireMock.stubFor(getSubject(getKeySubject(schema.getTopicToPublish()))
                .willReturn(getWithedHeader()
                        .withBody(new SchemaString(schema.getAvroKey()).toJson()))
        );

        WireMock.stubFor(getSubject(getValueSubject(schema.getTopicToPublish()))
                .willReturn(getWithedHeader()
                        .withBody(new SchemaString(schema.getAvroValue()).toJson()))
        );


        WireMock.stubFor(getSubjectVersion(getValueSubject(schema.getTopicToPublish()), schemaIndexId)
                .willReturn(getWithedHeader()
                        .withBody(new SchemaString(schema.getAvroValue()).toJson()))
        );

        WireMock.stubFor(getSubjectVersion(getKeySubject(schema.getTopicToPublish()), schemaIndexId)
                .willReturn(getWithedHeader()
                        .withBody(new SchemaString(schema.getAvroKey()).toJson())));

        return List.of(
                getKeySubject(schema.getTopicToPublish()).concat(" register on index :%s").formatted(schemaIndexId),
                getValueSubject(schema.getTopicToPublish()).concat(" register on index :%s").formatted(schemaIndexId)
        );
    }

    private static ResponseDefinitionBuilder getWithedHeader() {
        return aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    private static String formatAndGet(int schemaIndexId) {
        return "{\"id\":" + schemaIndexId + "}";
    }

    private static MappingBuilder getSubject(final String subject) {
        return WireMock.get(
                WireMock.urlPathMatching(ALL_SUBJECT_PATTERN + "/" + subject + "/versions/(?:latest|\\d+)"));
    }

    private static MappingBuilder postSubjectVersion(final String subject) {
        return WireMock.post(
                        WireMock.urlPathMatching(ALL_SUBJECT_PATTERN + "/" + subject + "/versions"))
                .withQueryParam("normalize", WireMock.matching(BOOLEAN_PATTERN));
    }

    private static MappingBuilder getSubjectVersion(final String subject, int schemaId) {
        return WireMock.get(
                        WireMock.urlPathMatching("/schemas/ids/" + schemaId))
                .withQueryParam("fetchMaxId", WireMock.matching(BOOLEAN_PATTERN))
                .withQueryParam("subject", WireMock.equalToIgnoreCase(subject));
    }

    private static String getKeySubject(final String topic) {
        return topic + "-key";
    }

    private static String getValueSubject(final String topic) {
        return topic + "-value";
    }

    private static List<Schema> schemas() {
        return List.of(
                new Schema("nl.nightcrawler.spring.kafkastandalone.avro.model.UUID", "nl.nightcrawler.spring.kafkastandalone.avro.model.Customer", "ota-exp-customer")
        );
    }

}
