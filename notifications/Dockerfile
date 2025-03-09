FROM gradle:jdk17 AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew :notifications:clean  :notifications:bootJar --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/notifications/build/libs/notifications-*.jar ./notifications.jar
ENTRYPOINT ["java", "-jar", "notifications.jar", "--spring.config.location=classpath:/application.yml"]