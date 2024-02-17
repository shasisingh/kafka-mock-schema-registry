package nl.nightcrawler.spring.kafkastandalone.api;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.nightcrawler.spring.kafkastandalone.service.MockBroker;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/v2/broker-registry")
@Slf4j
@AllArgsConstructor
public class BrokerController {
    private final MockBroker mockBroker;

    @PostMapping(path = "register-topic/{topicName}")
    public ResponseEntity<String> addTopic(@PathVariable("topicName") String topicName) {
        try {
            mockBroker.getEmbeddedKafkaHolder().instanceOfEmbeddedKafka().addTopics(new NewTopic(topicName, 1, (short) 1));
            return ResponseEntity.ok(topicName.concat(" Added to context"));
        } catch (Exception exception) {
            log.error("Error while adding new topic : {}",topicName,exception);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }
    }

}
