FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD storage-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]