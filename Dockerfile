FROM openjdk:17-jdk-alpine
LABEL authors="chaimabf"
WORKDIR /chaimabf
EXPOSE 8082
COPY target/tpAchatProject-1.0.jar tpAchatProject-1.0.jar
ENTRYPOINT ["java","-jar","target/tpAchatProject-1.0.jar"]

