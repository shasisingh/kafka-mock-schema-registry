FROM eclipse-temurin:17.0.10_7-jre-jammy AS builder
LABEL org.opencontainers.image.authors="RDC"
WORKDIR apps

ARG JAR_FILE=target/kafka-standalone.jar
COPY $JAR_FILE kafka-standalone.jar
ADD target/kafka-standalone.jar kafka-standalone.jar

RUN java -Djarmode=layertools -jar kafka-standalone.jar extract

FROM eclipse-temurin:17.0.10_7-jre-jammy

EXPOSE 10000
EXPOSE 10004
EXPOSE 10002

WORKDIR apps

COPY --from=builder apps/dependencies/ ./
RUN true
COPY --from=builder apps/snapshot-dependencies/ ./
RUN true
COPY --from=builder apps/spring-boot-loader/ ./
RUN true
COPY --from=builder apps/application/ ./

VOLUME ["/tmp","/keystores","/logs","/data"]

ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseG1GC -XX:+UseStringDeduplication"
ENTRYPOINT ["java" , "org.springframework.boot.loader.launch.JarLauncher"]

