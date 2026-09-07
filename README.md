# Local Services Directory API

A REST API for discovering local services — clinics, taxi ranks, government offices, libraries, and police stations — in the Soweto area. Built with Spring Boot as a portfolio project demonstrating production-ready API development.

## Features

- Full CRUD for categories and services
- Filter services by category ID
- Search services by name (case-insensitive)
- Pagination and sorting on list endpoints
- Input validation with custom error messages
- Global exception handling with clean JSON error responses
- DTO layer separating API contracts from entity structure
- Automatic database seeding on first startup
- Swagger UI documentation
- Unit tests for service layer

## Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 25 | Programming language |
| Spring Boot | 4.1.0 | Application framework |
| Spring Data JPA | 4.1.0 | Data access layer |
| MySQL | 8.x | Relational database |
| Maven | 3.x | Build tool |
| JUnit 5 | 5.x | Testing framework |
| Mockito | 5.x | Mocking framework |
| SpringDoc OpenAPI | 2.8.9 | API documentation |

## Getting Started

### Prerequisites

- JDK 25
- Maven 3.8+
- MySQL 8.x running locally
- NetBeans or any Java IDE

### Setup

1. Clone the repository:

   git clone https://github.com/yourusername/local-services-directory-api.git
   cd local-services-directory-api

2. Configure MySQL:
   - Create a database named local_service_directory_db
   - Update src/main/resources/application.properties with your MySQL credentials

3. Run the application:

   ./mvnw spring-boot:run

4. The app starts on port 8080. The database is automatically seeded with 5 categories and 11 services.

5. Open Swagger UI in your browser:

   http://localhost:8080/swagger-ui/index.html

   <img width="678" height="2185" alt="localhost_8080_swagger-ui_index html" src="https://github.com/user-attachments/assets/2514a09c-9e34-4881-aea3-2ff68abb0123" />

## API Endpoints

### Categories

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/categories | List all categories (paginated) |
| GET | /api/categories/{id} | Get category by ID |
| POST | /api/categories | Create a category |
| PUT | /api/categories/{id} | Update a category |
| DELETE | /api/categories/{id} | Delete a category |

### Services

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/services | List all services (paginated, filterable) |
| GET | /api/services/{id} | Get service by ID |
| POST | /api/services | Create a service |
| PUT | /api/services/{id} | Update a service |
| DELETE | /api/services/{id} | Delete a service |

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

<img width="1426" height="912" alt="image" src="https://github.com/user-attachments/assets/d6b6e167-a16f-44ea-9600-ff086d966519" />


## Example Request

curl -X POST http://localhost:8080/api/categories -H "Content-Type: application/json" -d "{\"name\": \"Clinics\"}"

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
| 404 | Resource Not Found | Entity with given ID does not exist |
| 409 | Data Integrity Violation | Attempting to delete a category that has services |
| 500 | Internal Server Error | Unexpected failure |

## Project Structure

src/main/java/co/za/obcodes/local_service_directory_api/

- LocalServiceDirectoryApiApplication.java
- model/
  - Category.java
  - Service.java
- dto/
  - CategoryDTO.java
  - ServiceDTO.java
- repository/
  - CategoryRepository.java
  - ServiceRepository.java
- controller/
  - CategoryController.java
  - ServiceController.java
- service/
  - CategoryService.java
  - ServiceService.java
- config/
  - DataSeeder.java
  - OpenApiConfig.java
- exception/
  - GlobalExceptionHandler.java
  - ResourceNotFoundException.java

## Architecture

Client (Postman, browser, or mobile app)
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
<img width="646" height="541" alt="image" src="https://github.com/user-attachments/assets/1e8767aa-0b14-46ae-9690-6f6a0318770a" />

## License

MIT License — feel free to use this code for learning or as a reference.
