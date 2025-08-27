# RFSHOP - BarberShop Management Platform

## About The Project

RFSHOP is a robust backend application designed to serve as a comprehensive platform for managing and booking appointments at barbershops. It provides a complete ecosystem for barbershop owners to manage their services and for users to discover, book, and review barbershops seamlessly.

The application is built with a clean, modular architecture, separating business logic from infrastructure details, making it scalable and easy to maintain.

## Features

*   **User Management:** Secure user registration and authentication with role-based access control (Customers and Barbers).
*   **BarberShop Management:** Allows users with a "BARBER" role to create, update, and manage their barbershop profiles.
*   **Booking System:** Enables clients to book, reserve, and cancel appointments.
*   **Reviews and Ratings:** Users can leave reviews and ratings for barbershops.
*   **Favorite Shops:** Users can maintain a list of their favorite barbershops for quick access.
*   **Image Handling:** Integration with Cloudinary for efficient image uploads for profiles and posts.
*   **AI Image Analysis:** Includes a module for intelligent image detection.
*   **Social Features:** Barbershops can create posts to showcase their work.

## Tech Stack

*   **Backend:** Java 17+
*   **Framework:** Spring Boot 3
*   **Containerization:** Docker & Docker Compose
*   **Database:** PostgreSQL 17
*   **Authentication:** Spring Security with JWT.
*   **Architecture:** Clean Architecture (Domain, Application, Infrastructure layers).
*   **Build Tool:** Maven

## Getting Started

This project is configured to run with Docker Compose, which is the recommended way to get started.

### Prerequisites

*   Docker
*   Docker Compose

### Running with Docker Compose (Recommended)

1.  **Clone the repository**
    ```sh
    git clone https://github.com/romanmeclazcke/rfshop.git
    cd rfshop
    ```

2.  **Build and run the services**
    From the root of the project, run the following command:
    ```sh
    docker-compose up --build
    ```
    This command will:
    *   Build the Java application Docker image.
    *   Start the `rfshop` application container.
    *   Start the `db_rfshop` PostgreSQL database container.

The application will be available at `http://localhost:8080`.

### Running Locally with Maven (Alternative)

For development purposes, you can also run the application directly using Maven.

1.  **Prerequisites**
    *   JDK 17 or later
    *   Maven
    *   A running instance of PostgreSQL.

2.  **Configure the database**
    - Open `src/main/resources/application.yml`
    - Ensure the `spring.datasource.url`, `username`, and `password` properties match your local database configuration.

3.  **Run the application**
    ```sh
    ./mvnw spring-boot:run
    ```
