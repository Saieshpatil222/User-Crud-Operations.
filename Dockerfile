FROM openjdk:17
LABEL maintainer="springbootrestfullwebservice"
ADD target/springboot-restful-webservices-0.0.1-SNAPSHOT.jar springboot-docker-user.jar
ENTRYPOINT ["java", "-jar", "springboot-docker-user.jar"]