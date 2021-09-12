FROM maven:3.8.1-openjdk-16 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:16
COPY --from=build /usr/src/app/target/conference-manager-1.0.jar /usr/app/conference-manager-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/conference-manager-1.0.jar"]