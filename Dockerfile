# Stage 1 : Build l'application
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copie les fichiers du projet
COPY . .

# Donne les droits d'exécution à mvnw et build l'application
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2 : Image légère pour exécuter l'app
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copie le jar généré depuis le stage de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]