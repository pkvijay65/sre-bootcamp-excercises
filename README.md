# Student Management API

A RESTful API for managing student records, built with Spring Boot following REST best practices and the Twelve-Factor App methodology.

## Features

- CRUD operations for student management
- API versioning
- Database migrations with Flyway
- Comprehensive error handling
- Health check endpoint
- Logging with appropriate log levels
- Unit tests
- Docker support
- Environment-based configuration

## Technologies

- Java 17
- Spring Boot 3.2.2
- PostgreSQL
- Flyway
- Gradle
- Docker

## Prerequisites

- JDK 17 or later
- Docker and Docker Compose
- Gradle 8.x
- PostgreSQL (if running locally)

## Local Setup

1. Clone the repository:
```bash
git clone https://github.com/pkvijay65/sre-bootcamp-excercises.git
cd sre-bootcamp-excercises
```

2. Set up the database:
```bash
make db-create
```

3. Build the application:
```bash
make build
```

4. Run the application:
```bash
make run
```

The API will be available at `http://localhost:8080`

## Environment Variables

The following environment variables can be configured:

- `DATABASE_URL`: JDBC URL for the database
- `DATABASE_USER`: Database username
- `DATABASE_PASSWORD`: Database password
- `LOG_LEVEL`: Logging level (default: INFO)

## API Endpoints

All endpoints are prefixed with `/api/v1`

### Student Management
- `GET /api/v1/students` - Get all students
- `GET /api/v1/students/{id}` - Get a student by ID
- `POST /api/v1/students` - Create a new student
- `PUT /api/v1/students/{id}` - Update a student
- `DELETE /api/v1/students/{id}` - Delete a student

### Health Check
- `GET /healthcheck` - Check API health

## Running Tests

```bash
make test
```

## Docker Support

Build the Docker image:
```bash
make docker-build
```

Run the container:
```bash
make docker-run
```

## Project Structure

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org.one2n.srebootcampexcercises
│   │   │       ├── controller
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       ├── exception
│   │   │       └── dto
│   │   └── resources
│   │       ├── db/migration
│   │       └── application.yml
│   └── test
└── build.gradle
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request
