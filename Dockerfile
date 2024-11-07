FROM openjdk:17-jdk-alpine
LABEL authors="chaimabf"
EXPOSE 8089
ARG JAR_FILE
COPY target/${JAR_FILE} /tpAchatProject.jar
ENTRYPOINT ["java", "-jar", "/tpAchatProject.jar"]
