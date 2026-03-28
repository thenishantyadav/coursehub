# CourseHub Backend

CourseHub is a Spring Boot backend for an online learning platform. It includes JWT authentication, role-based authorization, course management, student enrollment tracking, Swagger documentation, Docker support, and Kubernetes manifests.

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Lombok
- MySQL
- H2
- Swagger / OpenAPI
- Docker
- Kubernetes
- Maven

## Features

- JWT-based signup and login
- Role-based access with `ADMIN` and `STUDENT`
- Admin-only course creation
- Public course listing
- Student enrollment and progress tracking
- Swagger UI for API testing
- Dev profile with H2 for local runs
- Dockerized packaging
- Kubernetes deployment manifests

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

## Run Locally

### Dev Profile with H2

```bash
mvn spring-boot:run "-Dspring-boot.run.profiles=dev"
```

Application URLs:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI docs: `http://localhost:8080/v3/api-docs`
- H2 console: `http://localhost:8080/h2-console`

### Default Profile with MySQL

Provide these environment variables before running:

```properties
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/coursehub_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_mysql_password
APPLICATION_SECURITY_JWT_SECRET_KEY=your_base64_encoded_secret
```

Then run:

```bash
mvn spring-boot:run
```

## Docker

Build the image:

```bash
docker build -t coursehub:latest .
```

Run the container:

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/coursehub_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC" \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=your_mysql_password \
  -e APPLICATION_SECURITY_JWT_SECRET_KEY=your_base64_encoded_secret \
  coursehub:latest
```

## Kubernetes

Manifest files are available in [kubernetes](./kubernetes).

Apply them in this order:

```bash
kubectl apply -f kubernetes/mysql-deployment.yaml
kubectl apply -f kubernetes/mysql-service.yaml
kubectl apply -f kubernetes/coursehub-configmap.yaml
kubectl apply -f kubernetes/coursehub-secret.yaml
kubectl apply -f kubernetes/coursehub-deployment.yaml
kubectl apply -f kubernetes/coursehub-service.yaml
```

Update these placeholders before deployment:

- `your-dockerhub-username/coursehub:latest`
- MySQL password
- JWT secret key

## Main API Endpoints

- `POST /api/auth/signup`
- `POST /api/auth/login`
- `GET /api/courses`
- `POST /api/courses`
- `POST /api/enrollments`
- `PUT /api/enrollments/{enrollmentId}/progress`
- `GET /api/enrollments/users/{userId}`

## Sample Requests

### Signup

```json
{
  "name": "Student One",
  "email": "student1@example.com",
  "password": "password123",
  "role": "STUDENT"
}
```

### Create Course

```json
{
  "title": "Spring Boot Masterclass",
  "description": "REST APIs with Spring Boot",
  "instructorName": "Jane Instructor"
}
```

### Enroll in Course

```json
{
  "courseId": 1
}
```
