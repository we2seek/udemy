FROM alpine
RUN apk add --no-cache java-cacerts openjdk17-jdk
VOLUME /tmp
COPY wait-for-port.sh /wait-for-port.sh
RUN chmod +x /wait-for-port.sh
# templated jar file for mvn docker:build plugin
#ADD maven/${project.build.finalName}.jar myapp.jar
# hardcoded jar file for Dockerfile or docker-compose.yml
ADD target/currency-exchange-service-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'

# waiting for naming-server and api-gateway to be available
# speed up container startup with urandom
ENTRYPOINT ["sh", "-c", "/wait-for-port.sh naming-server 8761 && /wait-for-port.sh api-gateway 8765 && java -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]