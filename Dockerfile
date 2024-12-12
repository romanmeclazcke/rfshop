FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/RFSHOP-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} RFSHOP.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "RFSHOP.jar"]