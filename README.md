# 🧬 MutantesGlobal – Detector de Mutantes (Examen MercadoLibre)

Proyecto desarrollado para la detección de mutantes en base a secuencias de ADN según el desafío propuesto por MercadoLibre.
Implementado con Spring Boot, validadores, servicios, API REST, H2, JPA y tests automáticos.

## 📘 Índice

1- Descripción del Problema
2- Requerimientos por Nivel
3- Tecnologías Utilizadas
4- Arquitectura General del Proyecto
5- Endpoints de la API
6- Ejemplos de Requests
7- Base de Datos H2
8- Test Automáticos
9- Cómo Ejecutar el Proyecto
10- Despliegue en Render
11- Estructura del Proyecto
12- Autor

## 🧩 Descripción del Problema

Magneto busca identificar mutantes mediante el análisis de secuencias de ADN.
Un humano es considerado mutante si en su secuencia de ADN se encuentran más de una secuencia de cuatro letras iguales consecutivas, en alguna de las siguientes direcciones:

Horizontal

Vertical

Diagonal ↘

Diagonal ↙

Las letras válidas del ADN son: A, T, C, G.

## 📜 Requerimientos por Nivel
### Nivel 1 – Algoritmo

Implementar la función que evalúa si un ADN corresponde a un mutante.

### Nivel 2 – API REST

Exponer un endpoint /mutant que acepte un JSON con el ADN y devuelva:

* 200 OK si es mutante
* 403 Forbidden si no es mutante

### Nivel 3 – Persistencia y Estadísticas

Agregar:
*Base de datos H2
*Entidad Dna
*Evitar guardar ADN duplicado
*Endpoint /stats que devuelva:

{
"count_mutant_dna": X,
"count_human_dna": Y,
"ratio": X/Y
}

🛠 Tecnologías Utilizadas

Java 17

Spring Boot

Spring Web

Spring Data JPA

Base de datos H2

Maven

JUnit 5

Mockito

🧱 Arquitectura General del Proyecto

Tu proyecto quedó organizado así:

example/
├── controllers/
│     ├── DnaController.java       → /mutant
│     ├── StatsController.java     → /stats
│
├── dto/
│     ├── DnaRequiest.java         → request del ADN
│     ├── DnaResponse.java         → respuesta del /mutant
│     └── StatsResponse.java       → respuesta del /stats
│
├── entities/
│     ├── Base.java                → clase base con campos comunes
│     ├── Dna.java                 → entidad que representa el ADN almacenado
│
├── repositories/
│     └── DnaRepository.java       → operaciones JPA contra H2
│
├── services/
│     ├── DnaService.java          → lógica de detección + guardado
│     └── StatsService.java        → estadísticas
│
├── validators/
│     ├── DnaValidator.java        → validaciones del ADN
│     └── ValidDna.java            → anotación personalizada
│
└── ParcialMagnetoApplication.java → clase principal de Spring Boot

🌐 Endpoints de la API
POST /mutant

Evalúa si un humano es mutante.

✔ 200 OK → Mutante
✘ 403 Forbidden → No mutante
⚠ 400 Bad Request → ADN inválido
GET /stats

Devuelve estadísticas acumuladas:

{
"count_mutant_dna": 10,
"count_human_dna": 15,
"ratio": 0.666
}

📤 Ejemplos de Requests
➤ POST /mutant (mutante)
POST http://localhost:8080/mutant
Content-Type: application/json

{
"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}


📥 Respuesta:

200 OK

➤ POST /mutant (no mutante)
{
"dna": ["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]
}


📥 Respuesta:

403 Forbidden

➤ POST /mutant (ADN inválido)
{
"dna": ["ATGZGA","CAGTGC"]
}


📥 Respuesta:

400 Bad Request

🗄 Base de Datos H2

Configurada en application.properties:

spring.datasource.url=jdbc:h2:mem:mutantesdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true


La consola está disponible en:

http://localhost:8080/h2-console


Usuario: sa
Contraseña: (vacía)

🧪 Test Automáticos

Incluye:

✔ Tests unitarios del servicio
✔ Tests de algoritmos
✔ Tests de integración con H2
✔ Tests del /mutant y /stats

Para ejecutarlos:

mvn test

🚀 Cómo Ejecutar el Proyecto

Clonar el repo:

git clone <url-repositorio>


Acceder al proyecto:

cd MutantesGlobal


Ejecutar:

mvn spring-boot:run


La API quedará disponible en:

http://localhost:8080

☁️ Despliegue en Render

En un servicio web:

Build Command:

mvn clean install


Start Command:

java -jar target/MutantesGlobal-0.0.1-SNAPSHOT.jar


Endpoints públicos:

https://<render-url>/mutant
https://<render-url>/stats

👤 Autor

Ramiro Dichara
Proyecto desarrollado para el examen de MercadoLibre y la cátedra correspondiente.
Implementación completa, validada y lista para despliegue.