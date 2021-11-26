FROM openjdk:8-jdk-alpine
ADD target/name-generator-service-*.jar name-generator-service.jar
COPY ./opentelemetry-javaagent.jar opentelemetry-javaagent.jar
EXPOSE 8080
ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-Dotel.resource.attributes=service.name=name-gen-service", "-Dotel.traces.exporter=jaeger", "-Dotel.exporter.jaeger.endpoint= jaeger:14250", "-jar","-Xms256m","-Xmx2048m","/name-generator-service.jar"]
