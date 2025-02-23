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
RUN ./gradlew :limits:clean :limits:bootJar -Pprofile=${PROFILE} --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
ARG profile=docker
ENV PROFILE=${profile}
COPY --from=build /app/limits/build/libs/limits-*.jar ./limits.jar
COPY --from=build /app/limits/src/main/resources/application-${PROFILE}.yml ./application.yml
ENTRYPOINT ["java", "-jar", "limits.jar", "--spring.config.location=classpath:/application.yml"]