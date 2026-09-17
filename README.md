# Local Services Directory API

A REST API for discovering local services — clinics, taxi ranks, government offices, libraries, and police stations — in the Soweto area. Built with Spring Boot as a portfolio project demonstrating production-ready API development.

**Live API:** https://local-services-api-production.up.railway.app

**Swagger UI:** https://local-services-api-production.up.railway.app/swagger-ui/index.html

## Features

- Full CRUD for categories and services
- Filter services by category ID
- Search services by name (case-insensitive)
- Pagination and sorting on list endpoints
- Input validation with custom error messages
- Global exception handling with clean JSON error responses
- DTO layer separating API contracts from entity structure
- JWT authentication with BCrypt password hashing
- Public read access, protected write access
- Audit fields (createdAt, updatedAt) on all entities
- Automatic database seeding on first startup
- Swagger UI documentation
- Unit tests for service layer
- Dockerized for consistent deployment

## Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 25 | Programming language |
| Spring Boot | 4.1.0 | Application framework |
| Spring Data JPA | 4.1.0 | Data access layer |
| Spring Security | 4.1.0 | Authentication and authorization |
| JJWT | 0.12.6 | JWT token generation and validation |
| MySQL | 8.x | Relational database |
| Maven | 3.x | Build tool |
| JUnit 5 | 5.x | Testing framework |
| Mockito | 5.x | Mocking framework |
| SpringDoc OpenAPI | 2.8.9 | API documentation |
| Docker | 29.x | Containerization |
| Railway | Cloud | Hosting and managed MySQL |

## Getting Started

### Prerequisites

- JDK 25
- Maven 3.8+
- MySQL 8.x running locally (for local development)
- Docker Desktop (for containerized deployment)
- NetBeans or any Java IDE

### Local Setup

1. Clone the repository:

   git clone https://github.com/kamogelo44/local-services-directory-api.git
   cd local-services-directory-api

2. Configure MySQL:
   - Create a database named local_service_directory_db
   - Update src/main/resources/application.properties with your MySQL credentials

3. Run the application:

   ./mvnw spring-boot:run

4. The app starts on port 8080. The database is automatically seeded with 5 categories and 11 services.

5. Open Swagger UI in your browser:

   http://localhost:8080/swagger-ui/index.html

### Docker Setup

1. Build and run both the app and MySQL:

   docker-compose up --build

2. The app is available at http://localhost:8080

3. Stop everything:

   docker-compose down

## Authentication

The API uses JWT authentication. Read operations are public. Write operations require a valid token.

### Register a User

POST /api/auth/register

{
    "username": "admin",
    "password": "secret123"
}

### Login

POST /api/auth/login

{
    "username": "admin",
    "password": "secret123"
}

Returns a JWT token. Include it in subsequent requests:

Authorization: Bearer YOUR_TOKEN_HERE

## API Endpoints

### Authentication

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | /api/auth/register | Register a new user | No |
| POST | /api/auth/login | Login and receive JWT | No |

### Categories

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | /api/categories | List all categories (paginated) | No |
| GET | /api/categories/{id} | Get category by ID | No |
| POST | /api/categories | Create a category | Yes |
| PUT | /api/categories/{id} | Update a category | Yes |
| DELETE | /api/categories/{id} | Delete a category | Yes |

### Services

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | /api/services | List all services (paginated, filterable) | No |
| GET | /api/services/{id} | Get service by ID | No |
| POST | /api/services | Create a service | Yes |
| PUT | /api/services/{id} | Update a service | Yes |
| DELETE | /api/services/{id} | Delete a service | Yes |

### Query Parameters for GET /api/services

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| categoryId | Long | null | Filter by category ID |
| search | String | null | Search by name (case-insensitive) |
| page | int | 0 | Page number (zero-based) |
| size | int | 10 | Records per page |
| sortBy | String | name | Field to sort by |
| sortDirection | String | asc | Sort direction (asc or desc) |

### Example: Get services

GET http://localhost:8080/api/services

## Example Request

curl -X POST http://localhost:8080/api/categories -H "Content-Type: application/json" -H "Authorization: Bearer YOUR_TOKEN" -d "{\"name\": \"Clinics\"}"

### Example Response

{
    "id": 6,
    "name": "Clinics"
}

## Error Responses

All errors follow the same structure:

{
    "timestamp": "2026-09-07T19:20:57.000",
    "status": 404,
    "error": "Resource Not Found",
    "message": "Category not found with id 999"
}

| Status | Error Type | When |
|--------|-----------|------|
| 400 | Validation Failed | Missing or invalid fields |
| 403 | Forbidden | Missing or invalid JWT token |
| 404 | Resource Not Found | Entity with given ID does not exist |
| 409 | Data Integrity Violation | Attempting to delete a category that has services |
| 500 | Internal Server Error | Unexpected failure |

## Project Structure

src/main/java/co/za/obcodes/local_service_directory_api/

- LocalServiceDirectoryApiApplication.java
- model/
  - Category.java
  - Service.java
  - User.java
- dto/
  - CategoryDTO.java
  - ServiceDTO.java
- repository/
  - CategoryRepository.java
  - ServiceRepository.java
  - UserRepository.java
- controller/
  - CategoryController.java
  - ServiceController.java
  - AuthController.java
- service/
  - CategoryService.java
  - ServiceService.java
  - UserService.java
- security/
  - JwtUtil.java
  - JwtAuthenticationFilter.java
- config/
  - DataSeeder.java
  - OpenApiConfig.java
  - SecurityConfig.java
- exception/
  - GlobalExceptionHandler.java
  - ResourceNotFoundException.java

## Architecture

Client (Postman, browser, or mobile app)
    |
    v
Security Filter (JWT validation)
    |
    v
Controller (HTTP layer: receives requests, sends responses)
    |
    v
Service (Business logic: validation, mapping, rules)
    |
    v
Repository (Data access: JPA queries)
    |
    v
Database (MySQL)

## Testing

Run all tests:

./mvnw test

Current test coverage:

| Test Class | Tests | What It Verifies |
|------------|-------|------------------|
| CategoryServiceTest | 4 | CRUD operations and exception handling |
| ServiceServiceTest | 5 | CRUD operations, category validation, exception handling |

Tests run: 10, Failures: 0, Errors: 0, Skipped: 0

## Deployment

The API is deployed on Railway using a Docker image hosted on GitHub Container Registry.

- **Image:** ghcr.io/kamogelo44/local-services-api:latest
- **Database:** Railway managed MySQL
- **Live URL:** https://local-services-api-production.up.railway.app

To deploy your own copy:

1. Push the image to GHCR:

   docker-compose build
   docker tag local-services-directory-api-app:latest ghcr.io/YOUR_USERNAME/local-services-api:latest
   docker push ghcr.io/YOUR_USERNAME/local-services-api:latest

2. Create a Railway project with a MySQL service

3. Add a new service from the Docker image

4. Set these environment variables:

   SPRING_DATASOURCE_URL=jdbc:mysql://${{MySQL.MYSQLHOST}}:${{MySQL.MYSQLPORT}}/${{MySQL.MYSQLDATABASE}}?allowPublicKeyRetrieval=true&useSSL=false
   SPRING_DATASOURCE_USERNAME=${{MySQL.MYSQLUSER}}
   SPRING_DATASOURCE_PASSWORD=${{MySQL.MYSQLPASSWORD}}
   SPRING_JPA_HIBERNATE_DDL_AUTO=update

5. Generate a public domain in Settings → Networking

## License

MIT License — feel free to use this code for learning or as a reference.
