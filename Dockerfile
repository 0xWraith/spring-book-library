FROM eclipse-temurin:17-jdk-alpine

# ARG DATABASE_HOST
# ARG DATABASE_PORT
# ARG DATABASE_NAME
# ARG DATABASE_USER
# ARG DATABASE_PASSWORD

ENV DATABASE_HOST ${DATABASE_HOST}
ENV DATABASE_PORT ${DATABASE_PORT}
ENV DATABASE_NAME ${DATABASE_NAME}
ENV DATABASE_USER ${DATABASE_USER}
ENV DATABASE_PASSWORD ${DATABASE_PASSWORD}

RUN apk add --no-cache curl unzip
RUN adduser -D dbs

WORKDIR /home/dbs

COPY . .

RUN curl -L https://services.gradle.org/distributions/gradle-7.6-bin.zip -o gradle.zip
RUN unzip gradle.zip -d gradle
RUN rm gradle.zip

ENV PATH="/home/dbs/gradle/gradle-7.6/bin:${PATH}"

RUN chmod +rwx gradle/gradle-7.6/bin/gradle
RUN /home/dbs/gradle/gradle-7.6/bin/gradle -p /home/dbs/dbs_assignment build -x test --debug

EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/home/dbs/dbs_assignment/build/libs/dbs-api.jar"]