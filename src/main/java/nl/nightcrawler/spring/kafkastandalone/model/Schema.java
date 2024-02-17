package nl.nightcrawler.spring.kafkastandalone.model;


public record Schema(String key, String value, String topicName) { }
