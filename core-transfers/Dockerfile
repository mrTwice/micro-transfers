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
RUN ./gradlew :core-transfers:clean :core-transfers:bootJar -Pprofile=${PROFILE} --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
ARG profile=docker
ENV PROFILE=${profile}
COPY --from=build /app/core-transfers/build/libs/core-transfers-*.jar ./core-transfers.jar
COPY --from=build /app/core-transfers/src/main/resources/application-${PROFILE}.yml ./application.yml
ENTRYPOINT ["java", "-jar", "core-transfers.jar", "--spring.config.location=classpath:/application.yml"]