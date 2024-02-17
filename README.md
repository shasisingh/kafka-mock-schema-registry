
---

# Mock Schema Registry/Kafka Broker API Documentation

This repository contains documentation for the MOCK Schema Registry API and embedded Broker, which can assist you in your Unit/Integration or System tests.

## Why Use a Mock Schema Registry for Kafka Testing in Java?

When testing Java applications that interact with Kafka, incorporating a mock schema registry is crucial for several reasons:

1. **Isolation**: It enables tests to run independently of external dependencies, ensuring that changes in the schema registry or Kafka don't impact test outcomes.

2. **Controlled Environment**: Developers can establish controlled testing environments without relying on a live registry or Kafka broker, allowing for precise testing of different scenarios.

3. **Speed**: Mock schema registries facilitate faster test execution by eliminating the need for network communication with a real registry or Kafka broker.

4. **Flexibility**: Developers can simulate various responses and behaviors from the schema registry, enabling comprehensive testing of different edge cases and error scenarios.

5. **Cost Reduction**: Utilizing mock schema registries reduces development and testing costs by eliminating the need to access and maintain real Kafka infrastructure.


## Introduction

The Schema Registry API is used to register schemas for topics within the system.

## Usage

To include the required dependency in your Maven project, add the following lines to your `pom.xml` file:

```xml
<dependency>
    <If you have dependency for you avro you can add as maven>
</dependency>
```

### Register Schema

To register a schema, make a POST request to the following endpoint:

```
http://127.0.0.1:10000/api/v2/schema-registry/register
```

#### Request Body

The request body should be in JSON format and include the following parameters:

- `key`: The key for the schema.
- `value`: The value representing the schema.
- `topicName`: The name of the topic associated with the schema.

Example Request Body:

```json
{
  "key": "nl.nightcrawler.spring.kafkastandalone.avro.model.UUID",
  "value": "nl.nightcrawler.spring.kafkastandalone.avro.model.Customer",
  "topicName": "topic5"
}
```

### Get Schema Mappings

To retrieve schema mappings, make a GET request to the following endpoint:

```
http://127.0.0.1:10000/api/v2/schema-registry/mappings
```

This endpoint returns a list of mappings between schema keys and values.

### Register Topic

To register a topic, make a POST request to the following endpoint:

```
http://127.0.0.1:10000/api/v2/broker-registry/register-topic/topic5
```

This endpoint allows you to register a new topic named `topic5`.

## Other Application Consumption

If other applications are consuming the registration endpoints, they should be aware of the following configurations:

### Schema Registry Configuration

- **Port**: `10002`

### Kafka Broker Server Configuration

- **Port**: `10004`

Make sure the consuming applications are configured to use these ports for communication.

### Default Schema Names

The following are the default schema names that are registered when the application starts:

- `ota-exp-topic1`

## Docker Build

To build a Docker image with a specific version number from the project root, run the following commands:

1. Run Maven clean install:
```
mvn clean install
```

2. Build the Docker image:
```
docker build -t kafka-standalone:v1.0 .
```

This command builds a Docker image named `kafka-standalone` with version `v1.0` from the project's root directory.

### Docker Run

To run the Docker image with the specified ports mapped, use the following command:

```
docker run -p 10000:10000 -p 10003:10003 -p 10004:10004 --name kafka-standalone -it kafka-standalone:v1.0
```

This command runs the Docker image named `kafka-standalone` with version `v1.0` and maps ports `10000`, `10003`, and `10004` to the corresponding ports inside the container.

## License

This project is licensed under the [MIT License](LICENSE).

---
