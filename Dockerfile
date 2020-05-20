FROM openjdk:11-jdk-slim
WORKDIR /
ADD /target/b3-news-api-0.0.1-SNAPSHOT.jar b3-news-api.jar
ENV AWS_ACCESS_KEY_ID=
ENV AWS_SECRET_ACCESS_KEY=
EXPOSE 8080
CMD java -jar b3-news-api.jar