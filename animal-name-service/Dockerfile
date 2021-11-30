FROM openjdk:8-jdk-alpine
ADD target/animal-name-service-*.jar animal-name-service.jar
ADD ./opentelemetry-javaagent.jar opentelemetry-javaagent.jar
ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-jar", "-Xms256m", "-Xmx2048m", "/animal-name-service.jar"]
