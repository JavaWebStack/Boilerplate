# Build project
FROM maven:3.6.0-jdk-8-slim AS build
WORKDIR /

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package

# Run server
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/webapp.jar /usr/local/lib/webapp.jar

RUN mkdir -p /root/.fonts
COPY Product%20Sans%20Regular.ttf /root/.fonts
RUN fc-cache -f -v

EXPOSE 80
ENTRYPOINT ["java","-jar","/usr/local/lib/webapp.jar", "start"]