.PHONY: build run test clean docker-build docker-run

# Variables
APP_NAME = student-api
VERSION = 1.0.0

# Gradle commands
build:
	./gradlew clean build

run:
	./gradlew bootRun

test:
	./gradlew test

clean:
	./gradlew clean

# Docker commands
docker-build:
	docker build -t $(APP_NAME):$(VERSION) .

docker-run:
	docker run -p 8080:8080 \
		-e DATABASE_URL=jdbc:postgresql://host.docker.internal:5432/studentdb \
		-e DATABASE_USER=postgres \
		-e DATABASE_PASSWORD=postgres \
		$(APP_NAME):$(VERSION)

# Database commands
db-create:
	docker run --name postgres-db -e POSTGRES_DB=studentdb -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:latest

db-stop:
	docker stop postgres-db

db-start:
	docker start postgres-db

db-remove:
	docker rm -f postgres-db