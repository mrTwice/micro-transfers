#FROM openjdk:17
#EXPOSE 8080
#ADD ./build/libs/*.jar mt-core-transfers.jar
#ENTRYPOINT ["java", "-jar", "/mt-core-transfers.jar"]

FROM gradle:jdk17 AS build
WORKDIR /app
ARG profile=docker
ENV PROFILE=${profile}
COPY . .
RUN chmod +x gradlew
RUN ./gradlew :notifications:clean  :notifications:bootJar -Pprofile=${PROFILE} --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
ARG profile=docker
ENV PROFILE=${profile}
COPY --from=build /app/notifications/build/libs/notifications-*.jar ./notifications.jar
COPY --from=build /app/notifications/src/main/resources/application-${PROFILE}.yml ./application.yml
ENTRYPOINT ["java", "-jar", "notifications.jar", "--spring.config.location=classpath:/application.yml"]