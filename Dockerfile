FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/FinTrack-0.0.1-SNAPSHOT.jar financemanger-v1.0.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar", "financemanger-v1.0.jar"]