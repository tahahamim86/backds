# === FINAL MERGED STAGE ===
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy backend JAR
COPY --from=backend-build /home/app/target/*.jar app.jar

# Copy Angular build output into Spring Boot's static folder
COPY --from=frontend-build /app/dist/doctorsina ./static

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
