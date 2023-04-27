# Tee clean ja build enne
#docker build -t word-cloud-worker:latest .
#docker tag word-cloud-worker estken/word-cloud-worker
#docker push estken/word-cloud-worker

FROM openjdk:17-alpine
RUN apk add --no-cache curl tar bash
WORKDIR /app
COPY ./build/libs/word-cloud-worker.jar /app/word-cloud-worker.jar
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true -Xmx256m"
EXPOSE 8081
ENTRYPOINT exec java $JAVA_OPTS -jar /app/word-cloud-worker.jar
