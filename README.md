# BFHL REST API

A production-ready Spring Boot REST API that processes an array of strings and classifies them into numbers, alphabets, and special characters.

---

## Technologies Used

- Java 17
- Spring Boot 3.2.5
- Maven
- Lombok
- Spring Validation
- JUnit 5 + MockMvc

---

## Project Structure

```
bfhl-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/bfhl/
│   │   │   ├── BfhlApplication.java
│   │   │   ├── config/CorsConfig.java
│   │   │   ├── controller/BfhlController.java
│   │   │   ├── dto/BfhlRequestDTO.java
│   │   │   ├── dto/BfhlResponseDTO.java
│   │   │   ├── dto/ErrorResponseDTO.java
│   │   │   ├── exception/BfhlException.java
│   │   │   ├── exception/GlobalExceptionHandler.java
│   │   │   ├── service/BfhlService.java
│   │   │   ├── service/impl/BfhlServiceImpl.java
│   │   │   └── util/DataProcessingUtil.java
│   │   └── resources/application.properties
│   └── test/java/com/example/bfhl/
│       ├── BfhlControllerIntegrationTest.java
│       ├── BfhlServiceTest.java
│       └── DataProcessingUtilTest.java
├── .gitignore
├── Procfile
├── pom.xml
└── README.md
```

---

## Setup Instructions

### Prerequisites
- Java 17+
- Maven 3.8+

---

## Maven Commands

```bash
# Compile
mvn compile

# Run tests
mvn test

# Package JAR
mvn clean package -DskipTests

# Run the app
mvn spring-boot:run
```

---

## Run Instructions

```bash
# Option 1: Maven
mvn spring-boot:run

# Option 2: JAR
mvn clean package -DskipTests
java -jar target/bfhl-1.0.0.jar
```

Server starts on `http://localhost:8080`

---

## API Endpoint

### POST /bfhl

**Request:**
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

**Response:**
```json
{
  "is_success": true,
  "user_id": "ansh_khatri_26052026",
  "email": "anshkhatri231038@acropolis.in",
  "roll_number": "0827CS231042",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

---

## CURL Command

```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

---

## Postman Testing

1. Open Postman
2. Method: `POST`
3. URL: `http://localhost:8080/bfhl`
4. Headers: `Content-Type: application/json`
5. Body (raw JSON):
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

---

## GitHub Push Instructions

```bash
git init
git add .
git commit -m "Initial commit: BFHL Spring Boot API"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/bfhl-api.git
git push -u origin main
```

---

## Deployment on Render

1. Push code to GitHub.
2. Go to [render.com](https://render.com) → New → Web Service.
3. Connect your GitHub repo.
4. Set:
   - **Build Command:** `mvn clean package -DskipTests`
   - **Start Command:** `java -jar target/bfhl-1.0.0.jar`
   - **Environment:** Java
5. Add environment variable: `PORT=8080`
6. Click **Create Web Service**.

---

## Deployment on Railway

1. Push code to GitHub.
2. Go to [railway.app](https://railway.app) → New Project → Deploy from GitHub.
3. Select your repo.
4. Railway auto-detects Maven. Set:
   - **Start Command:** `java -jar target/bfhl-1.0.0.jar`
5. Add env var: `PORT=8080`
6. Deploy.

---

## Final CURL Test

```bash
curl -X POST https://YOUR_DEPLOYED_URL/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["A", "ABCD", "DOE", "1", "3", "$"]}'
```
