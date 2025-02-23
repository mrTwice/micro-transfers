#FROM openjdk:17
#EXPOSE 8080
#ADD ./build/libs/*.jar mt-core-transfers.jar
#ENTRYPOINT ["java", "-jar", "/mt-core-transfers.jar"]

# Этап сборки
# Этап сборки
FROM gradle:jdk17 AS build
WORKDIR /app

# Передаем профиль через аргумент (например, dev)
ARG profile=dev
ENV PROFILE=${profile}

# Копируем исходный код и файлы сборки
COPY core-transfers/src ./src
COPY core-transfers/build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle

# Даем права на выполнение для gradlew
RUN chmod +x gradlew

# Собираем проект с указанием профиля
RUN ./gradlew :core-transfers:clean :core-transfers:bootJar -Pprofile=dev --no-daemon && find /app -name "*.jar"



# Этап выполнения
FROM eclipse-temurin:17-jre
WORKDIR /app

# Передаем профиль через аргумент (например, dev)
ARG profile=dev
ENV PROFILE=${profile}


# Копируем собранный JAR из этапа сборки
COPY --from=build /app/core-transfers/build/libs/core-transfers-*.jar ./core-transfers.jar

# Копируем конфигурационный файл для выбранного профиля
COPY --from=build /app/src/main/resources/application-${PROFILE}.yml ./application.yml

# Указываем точку входа
ENTRYPOINT ["java", "-jar", "core-transfers.jar", "--spring.config.location=classpath:/application.yml"]