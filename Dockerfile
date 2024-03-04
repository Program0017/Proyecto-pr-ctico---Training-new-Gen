FROM openjdk:21
VOLUME /temp
ENV IMG_PATH = /img
EXPOSE 8080
RUN mkdir -p /img
ADD foodAplication/target/foodAplication-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]