# Library

A web application for managing a personal book library 

## Tech Stack

- **Backend:** Java 23, Spring Boot 4, Spring Security, Spring Data JPA
- **Frontend:** Thymeleaf, HTML, CSS
- **Database:** H2 (in-memory)
- **Build tool:** Maven

## Getting Started

### Prerequisites

- Java 23+

### Run

On Windows (if `mvn` is not in PATH, use the Maven Wrapper included in the project):

```cmd
mvnw.cmd spring-boot:run
```

On macOS / Linux:

```bash
./mvnw spring-boot:run
```

The app will start at `http://localhost:8081`.


### Login

| Username | Password |
|----------|----------|
| admin    | admin    |

## Project Structure

```
src/
├── main/
│   ├── java/com/tetiana/libraryfinal/
│   │   ├── config/        # Security configuration
│   │   ├── controller/    # MVC controllers
│   │   ├── model/         # JPA entities (Book, Author)
│   │   ├── repository/    # Spring Data repositories
│   │   └── service/       # Business logic
│   └── resources/
│       ├── static/
│       │   ├── css/       # Stylesheets
│       │   └── img/       # Static images (placeholders)
│       ├── templates/     # Thymeleaf HTML templates
│       ├── application.properties
│       └── data.sql       # Initial data
uploads/                   # User-uploaded images (git-ignored)
```

## Notes

- Uploaded book covers are saved to `uploads/img/books/`
- Uploaded author photos are saved to `uploads/img/authors/`
- The database is in-memory (H2), so data resets on each restart