package nl.nightcrawler.spring.kafkastandalone.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SchemaDefinition {
    private String avroKey;
    private String avroValue;
    private String topicToPublish;
}
