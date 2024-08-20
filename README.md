# Karyalaya Employee Management System

## Overview

Karyalaya Employee Management System (KEMS) is a comprehensive web application designed to manage employee information, tasks, and performance reviews. This system is built using Spring Boot and MySQL, and it leverages Spring Security for role-based access control. The application supports CRUD operations for tasks and performance reviews, and provides role-based access for Admins, Managers, and Employees.

## Table of Contents

1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Entity Relationship](#entity-relationship)
4. [Getting Started](#getting-started)
5. [API Endpoints](#api-endpoints)
6. [Testing the Application](#testing-the-application)
7. [Future Improvements](#future-improvements)

## Features

- **Role-Based Access Control**: Different functionalities are available based on user roles (Admin, Manager, Employee).
- **Task Management**: Admins and Managers can create, update, and delete tasks. Employees can view tasks and update their status.
- **Performance Reviews**: Managers can add and update performance reviews for employees.
- **Swagger Integration**: API documentation and testing using Swagger UI.

## Technologies Used

- **Backend**: Spring Boot
- **Database**: MySQL
- **Security**: Spring Security with JWT Authentication
- **API Documentation**: Swagger
- **ORM**: Spring Data JPA

## Entity Relationship

### Entities and Relationships

| **Entity**       | **Description**                                | **Relationships**                    |
|------------------|------------------------------------------------|-------------------------------------|
| **User**         | Represents system users (Admin, Manager, Employee) | - One-to-Many with PerformanceReview <br> - One-to-Many with Task |
| **Employee**     | Represents employees, extends User functionality | - Many-to-One with PerformanceReview <br> - One-to-Many with Task |
| **Task**         | Represents tasks assigned to employees         | - Many-to-One with Employee          |
| **PerformanceReview** | Represents performance reviews for employees | - Many-to-One with Employee          |

### Database Schema Overview

1. **users** Table:
    - `id`: Integer (Primary Key)
    - `username`: String
    - `password`: String
    - `email`: String
    - `role`: Enum (ADMIN, MANAGER, EMPLOYEE)

2. **employees** Table:
    - `id`: Integer (Primary Key)
    - `name`: String
    - `password`: String
    - `email`: String
    - `role`: Enum (ADMIN, MANAGER, EMPLOYEE)
    - `department`: Enum (e.g., SALES, HR)
    - `salary`: Double

3. **tasks** Table:
    - `id`: Integer (Primary Key)
    - `title`: String
    - `description`: String
    - `status`: Enum (PENDING, IN_PROGRESS, COMPLETED)
    - `assigned_to`: Integer (Foreign Key referencing `employees`)

4. **performance_reviews** Table:
    - `id`: Integer (Primary Key)
    - `rating`: Integer
    - `feedback`: String
    - `review_date`: Date
    - `employee_id`: Integer (Foreign Key referencing `employees`)

## Getting Started

### Prerequisites

- Java 17 or later
- MySQL 8 or later
- Maven
- Postman or similar tool for testing APIs

### Setup

1. **Clone the Repository**

    ```bash
    git clone https://github.com/raaz2/Karyalaya-Employee-Management-System.git
    cd Karyalaya-Employee-Management-System
    ```

2. **Configure MySQL Database**

    - Create a new database and update the `application.properties` file with your database credentials.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. **Build and Run the Application**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Access Swagger UI**

   Open your browser and navigate to `http://localhost:8080/swagger-ui.html` to explore and test the API endpoints.

## API Endpoints

### User Endpoints

- **POST** `/auth/register`
    - **Request Body**: User details (e.g., username, password, email, role)
    - **Description**: Register a new user.

- **POST** `/auth/login`
    - **Request Body**: Credentials (e.g., username, password)
    - **Description**: Login and receive JWT token.

### Task Endpoints

- **POST** `/tasks`
    - **Request Body**: Task details
    - **Description**: Create a new task (Manager role required)

- **PUT** `/tasks/{id}`
    - **Request Body**: Updated task details
    - **Description**: Update an existing task (Manager role required)

- **DELETE** `/tasks/{id}`
    - **Description**: Delete a task (Manager role required)

- **GET** `/tasks/employee/{employeeId}`
    - **Description**: Get tasks assigned to a specific employee.

- **GET** `/tasks/{id}`
    - **Description**: Get task details by ID.

- **PUT** `/tasks/{id}/status`
    - **Request Parameter**: `status` (Task status enum)
    - **Description**: Update the status of a task (Employee role required)

### Performance Review Endpoints

- **POST** `/performance`
    - **Request Body**: Performance review details
    - **Description**: Add a new performance review (Manager role required)

- **PUT** `/performance/{id}`
    - **Request Body**: Updated performance review details
    - **Description**: Update an existing performance review (Manager role required)

- **GET** `/performance/employee/{employeeId}`
    - **Description**: Get performance reviews for a specific employee.

## Testing the Application

- Use Postman or Swagger UI to test the endpoints.
- Ensure you have the correct roles assigned to your users to access specific endpoints.

## Future Improvements

- **Enhanced Reporting**: Implement detailed reporting and analytics features.
- **Notification System**: Add email or in-app notifications for task updates and performance reviews.
- **User Management**: Improve user management and administration capabilities.

