FROM openjdk:11
WORKDIR /app
COPY estoque-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "/app/estoque-0.0.1-SNAPSHOT.jar"]
EXPOSE 8090