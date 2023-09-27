FROM openjdk:17-jdk-slim
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/*.jar /opt/intuit.jar
ENTRYPOINT exec java $JAVA_OPTS -jar intuit.jar