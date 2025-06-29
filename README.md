# Thaillo

Thaillo is a Trello-like project management application built with Spring Boot, Spring Security, JWT authentication, PostgreSQL, and Lombok.

## Features

- User registration and login with JWT authentication
- Secure endpoints for boards and task lists
- PostgreSQL database integration
- BCrypt password encryption
- RESTful API design

## Technologies

- Java 21
- Spring Boot 3.5
- Spring Security
- JWT (io.jsonwebtoken)
- PostgreSQL
- Lombok
- Maven

## Getting Started

### Prerequisites

- Java 21+
- Maven
- Docker (for PostgreSQL and pgAdmin)

### Setup

1. **Clone the repository**

   ```sh
   git clone <your-repo-url>
   cd Thaillo
   ```

2. **Start PostgreSQL and pgAdmin with Docker**

   ```sh
   cd src/main/resources
   docker compose up -d
   ```

   - PostgreSQL: `localhost:5432` (user: `admin`, password: `admin`)
   - pgAdmin: `localhost:8080` (user: `admin@admin.com`, password: `admin`)

3. **Configure application properties**

   The default configuration is set in [`src/main/resources/application.properties`](src/main/resources/application.properties):

   ```
   server.port=8090
   spring.datasource.url=jdbc:postgresql://localhost:5432/thaillo
   spring.datasource.username=admin
   spring.datasource.password=admin
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=<code you want use>
   ```

4. **Build and run the application**

   ```sh
   ./mvnw spring-boot:run
   ```

   The API will be available at `http://localhost:8090`.

## API Usage

### Authentication

- **Register:** `POST /api/auth/register`
- **Login:** `POST /api/auth/login`

Both endpoints accept JSON bodies:

```json
{
  "name": "Your Name",      // Only for register
  "email": "user@example.com",
  "password": "yourpassword"
}
```

**Response:**  
A JWT token is returned in the response body.

### Using JWT Token

For all protected endpoints, include the token in the `Authorization` header:

```
Authorization: Bearer <your_jwt_token>
```

### Board Endpoints

- `GET /board/` — List all boards
- `GET /board/{id}` — Get board by ID
- `POST /board/` — Create a board
- `PUT /board/{id}` — Update a board
- `DELETE /board/{id}` — Delete a board

### Task List Endpoints

- `GET /task_list/` — List all task lists
- `GET /task_list/{id}` — Get task list by ID
- `POST /task_list/` — Create a task list
- `PUT /task_list/{id}` — Update a task list
- `DELETE /task_list/{id}` — Delete a task list

## Development

- The project uses Lombok for boilerplate code reduction.
- Entities implement `UserDetails` for Spring Security integration.
- JWT tokens are generated and validated using a secret key.

## Troubleshooting

- Ensure the JWT token is sent in the `Authorization` header, **not** in the body.
- If you get `403 Forbidden`, check that your token is valid and not expired.
- Database connection errors? Make sure Docker containers are running.

## License

This project is for educational purposes.
