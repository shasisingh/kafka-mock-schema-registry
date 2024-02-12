
---

# Schema Registry API Documentation

This repository contains documentation for the Schema Registry API.

## Introduction

The Schema Registry API is used to register schemas for topics within the system.

## Usage

To include the required dependency in your Maven project, add the following lines to your `pom.xml` file:

```xml
<dependency>
    <groupId>nl.rabobank.beb</groupId>
    <artifactId>business-event-bus-schema</artifactId>
    <version>version your other app using</version>
</dependency>

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
  "key": "nl.rabobank.beb.general.Random",
  "value": "nl.rabobank.beb.payments.sfc.SfcSubscriptionExecutionEvent",
  "topicName": "rabo-ota-local-payments-sfcsubscriptionexecution1"
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
http://127.0.0.1:10000/api/v2/broker-registry/register-topic/topic2
```

This endpoint allows you to register a new topic named `topic2`.

## Other Application Consumption

If other applications are consuming the registration endpoints, they should be aware of the following configurations:

### Schema Registry Configuration

- **Port**: `10002`

### Kafka Broker Server Configuration

- **Port**: `10004`

Make sure the consuming applications are configured to use these ports for communication.

### Default Schema Names

The following are the default schema names that are registered when the application starts:

- `rabo-ota-local-payments-multibankaccountupdated`
- `rabo-ota-local-payments-sfcsubscriptiondelete`
- `rabo-ota-local-payments-sfcsubscriptionchange`
- `rabo-ota-local-payments-sfcsubscriptionexecution`
- `rabo-ota-local-rdc-powerofattorneyonlineregistration-deleteaccount`

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
