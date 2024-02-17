package nl.nightcrawler.spring.kafkastandalone.api;

import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.nightcrawler.spring.kafkastandalone.service.MockSchemaRegistry;
import nl.nightcrawler.spring.kafkastandalone.utils.AvroHelper;
import nl.nightcrawler.spring.kafkastandalone.model.Schema;
import nl.nightcrawler.spring.kafkastandalone.model.SchemaDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v2/schema-registry")
@Slf4j
@AllArgsConstructor
public class RegistryController {

    private final MockSchemaRegistry mockSchemaRegistry;

    @PostMapping(path = "register")
    public ResponseEntity<String> registerSchema(@RequestBody Schema schema) {
        var schemaDefinition = SchemaDefinition.
                builder()
                .avroKey(AvroHelper.getSchema(schema.key()).toString())
                .avroValue(AvroHelper.getSchema(schema.value()).toString())
                .topicToPublish(schema.topicName())
                .build();
        try {
            List<String> registered = mockSchemaRegistry.registerNewSchemaPerTopic(schemaDefinition);
            log.info("New schema registered : {}", registered);
            return ResponseEntity.ok("Success");
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }

    @GetMapping(path = "mappings")
    public ResponseEntity<List<String>> allMappings() {
        return ResponseEntity.
                ok(
                        mockSchemaRegistry
                                .getWireMockServer()
                                .getStubMappings()
                                .stream()
                                .map(StubMapping::toString)
                                .toList()
                );
    }
}
