FROM openjdk:8-jdk-alpine
ADD target/animal-name-service-*.jar animal-name-service.jar
ADD ./opentelemetry-javaagent.jar opentelemetry-javaagent.jar
EXPOSE 9000
ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-Dotel.resource.attributes=service.name=animal-name-gen-serv", "-jar", "-Xms256m", "-Xmx2048m", "/animal-name-service.jar"]
