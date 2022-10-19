FROM openjdk:17
EXPOSE 8080
COPY /target/*.jar /usr/share/app.jar
ENTRYPOINT ["java", "-jar", "usr/share/app.jar"]