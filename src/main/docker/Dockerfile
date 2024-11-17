FROM openjdk:17-jdk-slim

EXPOSE 8089
EXPOSE 5005


COPY library-management-service-1.0.0.jar /app.jar

CMD ["/usr/local/openjdk-17/bin/java", "-jar", "-Dspring.profiles.active=docker", "-Djdk.tls.client.protocols=TLSv1.2", "/app.jar"]


