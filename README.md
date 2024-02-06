# BS23


# User Management System with Kafka Integration and Docker Containers

This project is a Java-based user management system that utilizes Spring Boot, JPA, MySQL, Redis, and Kafka to perform CRUD operations on user entities. Additionally, it includes functionality to produce events using Kafka when a user is created, updated, or deleted, and consumes these events to save `LogCrudEntity` into the database. The entire system is containerized using Docker.

## Features

- CRUD operations on user entities.
- Kafka integration to produce events for user creation, update, and deletion.
- Consumption of Kafka events to save `LogCrudEntity` into the database.
- Integration with MySQL database using Spring Data JPA.
- Integration with Redis for caching user data.
- Containerization with Docker for easy deployment and scalability.

## Components

### 1. User Entity
The `UserEntity` represents the data model for users in the system.

### 2. UserController
The `UserController` handles incoming HTTP requests related to user operations such as create, read, update, and delete.

### 3. UserService
The `UserService` contains the business logic for user-related operations. It interacts with the `UserRepository` to perform CRUD operations on `UserEntity`. Additionally, it utilizes Redis for caching user data.

### 4. UserRepository
The `UserRepository` interfaces with the MySQL database using Spring Data JPA to perform CRUD operations on `UserEntity`.

### 5. Kafka Integration
The project integrates with Kafka to produce events when a user is created, updated, or deleted. It also consumes these events to save `LogCrudEntity` into the database.

### 6. LogCrudEntity
The `LogCrudEntity` represents the logs generated upon user CRUD operations. These logs are saved into the MySQL database upon consumption of Kafka events.

### 7. Docker
The entire application is containerized using Docker, which simplifies deployment and ensures consistent runtime environments across different platforms.

## Dependencies

- Spring Boot Starter Data JPA: For easy integration with JPA and MySQL database.
- MySQL Connector Java: Driver for connecting to the MySQL database.
- Lombok: Reduces boilerplate code by generating getters, setters, constructors, and other repetitive code.
- Spring Boot Starter Data Redis: For integrating with Redis for caching user data.
- Spring Kafka: For integrating Kafka messaging into the Spring application.

## Getting Started

To run this project locally using Docker, follow these steps:

1. Clone the repository:

https://github.com/MobashyerHossain/BS23

2. open terminal on project directory.
3. use command docker build -t mobashyer/dockertest:1.0.1 .
4. use command docker-compose up -d
5. after container is up and running we can use postman to do crud operation on user entity like,
    a. to get all users http://host.docker.internal:8080/users with Get Method
    b. to create user http://host.docker.internal:8080/users with Post Method
    c. to update user http://host.docker.internal:8080/users/{id} with Put Method
    d. to delete user http://host.docker.internal:8080/users/{id} with Delete Method
6. We can view the `LogCrud` records which keeps tracks of all the crud operations of `UserEntity` data consumed from `user-events` kafka topic using http://host.docker.internal:8080/logCruds
