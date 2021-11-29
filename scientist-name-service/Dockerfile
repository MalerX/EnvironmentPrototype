FROM openjdk:8-jdk-alpine
ADD target/scientist-name-service-*.jar scientist-name-service.jar
ADD ./opentelemetry-javaagent.jar opentelemetry-javaagent.jar
ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-jar", "-Dotel.resource.attributes=service.name=scient-name-gen-serv" , "-Xms256m","-Xmx2048m", "/scientist-name-service.jar"]
