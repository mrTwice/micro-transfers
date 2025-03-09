FROM gradle:jdk17 AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew :limits:clean :limits:bootJar --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/limits/build/libs/limits-*.jar ./limits.jar
ENTRYPOINT ["java", "-jar", "limits.jar", "--spring.config.location=classpath:/application.yml"]