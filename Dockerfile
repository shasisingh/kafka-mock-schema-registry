FROM eclipse-temurin:17.0.10_7-jre-jammy AS builder
LABEL org.opencontainers.image.authors="Shasi Singh"
WORKDIR standalone-app

ARG JAR_FILE_LOCATION=target/kafka-standalone.jar

COPY $JAR_FILE_LOCATION kafka-standalone.jar
ADD  $JAR_FILE_LOCATION kafka-standalone.jar

RUN java -Djarmode=layertools -jar kafka-standalone.jar extract

FROM eclipse-temurin:17.0.10_7-jre-jammy

EXPOSE 10000
EXPOSE 10004
EXPOSE 10002

WORKDIR standalone-app

COPY --from=builder standalone-app/dependencies/ ./
RUN true
COPY --from=builder standalone-app/snapshot-dependencies/ ./
RUN true
COPY --from=builder standalone-app/spring-boot-loader/ ./
RUN true
COPY --from=builder standalone-app/application/ ./

VOLUME ["/tmp","/logs","/data"]

ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseG1GC -XX:+UseStringDeduplication"
ENTRYPOINT ["java" , "org.springframework.boot.loader.launch.JarLauncher"]

