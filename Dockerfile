# Usamos JDK 17 oficial
FROM eclipse-temurin:17-jdk

# Carpeta de trabajo
WORKDIR /app

# Copiamos todos los archivos del repo
COPY . .

# Damos permisos al gradlew
RUN chmod +x gradlew

# Construimos la app
RUN ./gradlew build -x test

# Comando para ejecutar la app
# Cambia 'tu-app.jar' por el nombre exacto del JAR que genera Gradle
CMD ["java", "-jar", "build/libs/tu-app.jar"]
