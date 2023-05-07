FROM openjdk:17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

FROM openjdk:17
ARG JAR_FILE=build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]
EXPOSE 9999
