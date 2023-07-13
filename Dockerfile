# define base docker image
FROM openjdk:18.0.1
LABEL maintainer="ololadeguides.net"
EXPOSE 8090
ADD target/onlinefooddelivery-0.0.1-SNAPSHOT.jar onlinefooddelivery.jar
ENTRYPOINT ["java", "-jar", "onlinefooddelivery.jar"]


