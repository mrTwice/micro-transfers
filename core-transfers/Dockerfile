FROM gradle:jdk17 AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew :core-transfers:clean :core-transfers:bootJar --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/core-transfers/build/libs/core-transfers-*.jar ./core-transfers.jar
ENTRYPOINT ["java", "-jar", "core-transfers.jar", "--spring.config.location=classpath:/application.yml"]