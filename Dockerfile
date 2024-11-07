FROM openjdk:17-jdk-alpine
LABEL authors="chaimabf"
EXPOSE 8089
ADD target/tpachatproject-1.0.jar tpachatproject-1.0.jar
ENTRYPOINT ["java","-jar","target/tpAchatProject-1.0.jar"]

