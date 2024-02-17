package nl.nightcrawler.spring.kafkastandalone.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AvroHelper {

    public static org.apache.avro.Schema getSchema(String clazz) {
        try {
            var randomClass = Thread.currentThread().getContextClassLoader().loadClass(clazz);
            var getClassSchema = randomClass.getMethod("getClassSchema");
            return (org.apache.avro.Schema) getClassSchema.invoke(randomClass.getDeclaredConstructor().newInstance());
        } catch (ClassNotFoundException e) {
            log.error("Could not find class for name {}", clazz);
        } catch (Exception e) {
            log.error("Could not create a Schema from class {}", clazz, e);
        }
        throw new RuntimeException("Unable to retrieve provided schema class(%s) from context".formatted(clazz));
    }
}
