FROM gradle:8-jdk21 AS build

WORKDIR /home/bat/IdeaProjects/bookmanagementsys

COPY --chown=gradle:gradle . .

RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /home/bat/IdeaProjects/bookmanagementsys/build/libs/bookmanagementsys-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]