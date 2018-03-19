# to run
# docker build -t Dockerfile --build-arg JAR_FILE=target/sandpit-0.0.1-SNAPSHOT.jar .
# docker run -p 8080:8080  -i --name blah dockerfile
# curl http://user:password@localhost:8080/rest/blah/v1

#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/sandpit-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

EXPOSE 8080

