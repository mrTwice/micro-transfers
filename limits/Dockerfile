#FROM openjdk:17
#EXPOSE 8080
#ADD ./build/libs/*.jar mt-core-transfers.jar
#ENTRYPOINT ["java", "-jar", "/mt-core-transfers.jar"]

# Этап сборки
# Этап сборки
# Этап сборки
FROM gradle:jdk17 AS build
WORKDIR /app

# Копируем исходный код и файлы сборки
COPY limits/src ./src
COPY limits/build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle

# Передаем профиль через аргумент (например, dev)
ARG profile=dev
ENV PROFILE=${profile}

# Собираем проект с указанием профиля
RUN ./gradlew :limits:build -Pprofile=${PROFILE} --no-daemon

# Этап выполнения
FROM eclipse-temurin:17-jre
WORKDIR /app

# Копируем собранный JAR из этапа сборки
COPY --from=build /app/limits/build/libs/limits-*.jar ./limits.jar

# Копируем конфигурационный файл для выбранного профиля
COPY --from=build /app/limits/src/main/resources/application-${PROFILE}.yml ./application.yml

# Указываем точку входа
ENTRYPOINT ["java", "-jar", "limits.jar", "--spring.config.location=classpath:/application.yml"]