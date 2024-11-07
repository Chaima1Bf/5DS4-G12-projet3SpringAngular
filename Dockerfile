FROM openjdk:17-jdk-alpine
LABEL authors="Chaima-Bf"
EXPOSE 8082
ADD target/tpAchatProject-1.0.jar tpAchatProject-1.0.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-1.0.jar"]

