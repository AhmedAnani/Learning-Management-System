# Learning Management System (LMS)

A robust and scalable Learning Management System backend built with **Spring Boot** and **Java 21**. This system provides a comprehensive APIs for managing courses, users, content, and assessments, designed to power modern e-learning platforms.

## Key Features

*   **Course Management**: Create and structure courses with sections and varied content types.
*   **Content Diversity**: Support for multiple content formats including **Videos**, **Articles**, and **Quizzes**.
*   **User & Role Management**: Secure user registration and authentication with Role-Based Access Control (RBAC). Manage Permissions dynamically.
*   **Authentication & Security**: State-of-the-art security using **Spring Security** and **JWT** (JSON Web Tokens) for stateless authentication.
*   **Assessment System**: Built-in quiz engine to evaluate learner progress.
*   **Database Migrations**: Automated schema management using **Flyway**, ensuring consistent database states across environments.
*   **Email Integration**: Integrated email services for notifications and communication.

##  Tech Stack

*   **Language**: Java 21
*   **Framework**: Spring Boot 3.5.7
*   **Database**: PostgreSQL
*   **Security**: Spring Security, JWT (jjwt)
*   **ORM**: Hibernate (Spring Data JPA)
*   **Migration**: Flyway
*   **Build Tool**: Maven

##  Getting Started

### Prerequisites

*   JDK 21
*   Maven
*   PostgreSQL

### Installation

1.  **Clone the repository**
    ```bash
    git clone https://github.com/AhmedAnani/Learning-Management-System.git
    cd Learning-Management-System
    ```

2.  **Configure Database**
    Update `src/main/resources/application.properties` (or `application.yml`) with your PostgreSQL credentials:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3.  **Build the project**
    ```bash
    ./mvnw clean install
    ```

4.  **Run the application**
    ```bash
    ./mvnw spring-boot:run
    ```


