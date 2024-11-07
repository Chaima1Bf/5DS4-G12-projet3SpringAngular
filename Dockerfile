FROM openjdk:17-jdk-alpine
LABEL authors="chaimabf"
EXPOSE 8089
COPY target/tpAchatProject-1.0-SNAPSHOT.jar /tpAchatProject.jar
ENTRYPOINT ["java", "-jar", "/tpAchatProject.jar"]

