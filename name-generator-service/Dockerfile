FROM openjdk:8-jdk-alpine
ADD target/name-generator-service-*.jar name-generator-service.jar
ADD ./opentelemetry-javaagent.jar opentelemetry-javaagent.jar
ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-jar", "-Xms256m","-Xmx2048m", "/name-generator-service.jar"]
