FROM  openjdk:21-jdk-slim
RUN mkdir /app
workdir /app
copy target/*.jar  app.jar
cmd ["java","-jar","app.jar"]