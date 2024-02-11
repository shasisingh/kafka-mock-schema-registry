
---

# Schema Registry API Documentation

This repository contains documentation for the Schema Registry API.

## Introduction

The Schema Registry API is used to register schemas for topics within the system.

## Usage

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

## License

This project is licensed under the [MIT License](LICENSE).

---
