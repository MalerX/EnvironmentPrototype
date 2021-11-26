FROM openjdk:8-jdk-alpine
ADD target/animal-name-service-*.jar animal-name-service.jar
ENTRYPOINT ["java", "-jar", "-Xms256m", "-Xmx2048m", "/animal-name-service.jar"]
