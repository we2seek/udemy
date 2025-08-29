FROM alpine
RUN apk add --no-cache java-cacerts openjdk17-jdk
VOLUME /tmp
# templated jar file for mvn docker:build plugin
#ADD maven/${project.build.finalName}.jar myapp.jar
# hardcoded jar file for Dockerfile or docker-compose.yml
ADD target/naming-server-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
# speed up container startup with urandom
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

### build
# docker build -t we2seek/naming-server:latest .
### run
# docker run -d --rm -p 8000:8000 we2seek/naming-server:latest