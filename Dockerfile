FROM openjdk:17-oracle
ADD target/city-task.jar city-task.jar
ENTRYPOINT ["java", "-jar","city-task.jar"]
EXPOSE 8080