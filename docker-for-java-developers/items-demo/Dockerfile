FROM alpine
RUN apk add --no-cache java-cacerts openjdk17-jdk
VOLUME /tmp
# templated jar file for mvn docker:build plugin
#ADD maven/${project.build.finalName}.jar myapp.jar
# hardcoded jar file for Dockerfile or docker-compose.yml
ADD target/items-demo-0.0.3-SNAPSHOT.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
# speed up container startup with urandom
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]