FROM openjdk:17-jdk-alpine

RUN mkdir /appp
WORKDIR  /appp
COPY  target/*.jar /app/app.jar

CMD ["java","-jar","/app/app.jar"]
