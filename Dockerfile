FROM amazoncorretto:11
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV PROFILE="prod"
EXPOSE 80/tcp
ENTRYPOINT java -jar -Dspring.profiles.active=${PROFILE} /app.jar