FROM openjdk:8-jdk-alpine
ADD target/scientist-name-service-*.jar scientist-name-service.jar
COPY ./opentelemetry-javaagent.jar opentelemetry-javaagent.jar
EXPOSE 8090
ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-Dotel.resource.attributes=service.name=scientist-service", "-Dotel.traces.exporter=jaeger", "-Dotel.exporter.jaeger.endpoint= jaeger:14250", "-jar","-Xms256m","-Xmx2048m","/scientist-name-service.jar"]
