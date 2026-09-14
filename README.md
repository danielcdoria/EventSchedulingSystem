# Event Scheduling System

REST API for event management with JWT authentication. Users can create events, register for them, and filter by location or status.

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Docker
- JUnit 5 + Mockito

## Features

- User registration and login with JWT token
- Create and manage events
- Register for events (ManyToMany relationship)
- Filter events by location or status
- Access control — users can only interact with their own events
- Unit tests for AuthService and EventService

## Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Login and receive JWT token |

### Events
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/events` | List all events for the logged user |
| POST | `/events` | Create a new event |
| GET | `/events/{id}` | Get event by ID |
| DELETE | `/events/{id}` | Delete an event |
| GET | `/events/location?location=` | Filter events by location |
| GET | `/events/status?status=` | Filter events by status |

### Event Status
`UPCOMING` `IN_PROGRESS` `FINISHED` `CANCELLED`

## Running Locally

**1. Start the database:**
```bash
docker-compose up -d
```

**2. Run the application:**
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## Authentication

After registering or logging in, use the returned token in the `Authorization` header:

```
Authorization: Bearer <your-token>
```

## Environment Variables

| Variable | Default |
|----------|---------|
| `PGHOST` | localhost |
| `PGPORT` | 5440 |
| `PGDATABASE` | eventschedulingdb |
| `PGUSER` | postgres |
| `PGPASSWORD` | postgres |
| `JWT_SECRET` | *(default secret — change in production)* |
| `PORT` | 8080 |
