FROM openjdk

WORKDIR /app

COPY target/lista-github-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]