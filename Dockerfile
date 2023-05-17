FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/cryprocurrency-monitor-production.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]