# CourseHub Backend

CourseHub is a Spring Boot backend starter project built with Java 17 and Maven.
swsiwjswijwnws
## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Lombok
- MySQL
- Maven

## Project Structure

```text
com.coursehub
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
└── service
```

## Prerequisites

- Java 17 installed
- Maven installed
- MySQL running locally

## Configuration

Update the database credentials in [src/main/resources/application.properties](src/main/resources/application.properties):

```properties
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

## Run The Application

```bash
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

## Sample Endpoints

- `GET /api/courses`
- `GET /api/courses/{id}`
- `POST /api/courses`

## Sample Request

```json
{
  "title": "Spring Boot Fundamentals",
  "description": "Learn the basics of building REST APIs with Spring Boot.",
  "instructor": "Jane Doe"
}
```
