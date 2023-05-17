# CryptoCurrencyMonitor

CryptoCurrencyMonitor is a simple REST service for viewing cryptocurrency quotes. It retrieves the latest prices for cryptocurrencies from an external API, stores them in a relational database, and provides endpoints for users to access and monitor the prices.

## Functionality

The following functionality is provided by the CryptoCurrencyMonitor service:
- View a list of available cryptocurrencies.
- View the current price for a specified cryptocurrency
- Log a warning message when the price of a registered cryptocurrency changes by more than 1%.
- Users can register themselves using the  REST method by providing their username and the cryptocurrency code.
  Whenever the price changes by more than 1%, a warning message is logged, including the currency code, username, and percentage change since registration.

## Requirements

To run the project, ensure that you have the following software installed:

- Java Development Kit (JDK) 17 or higher.
- Apache Maven. It is required for local building and running of the application.
- Docker: If you choose to run the application as a Docker container, Docker needs to be installed on your system.

Make sure you have the necessary software installed and configured before proceeding with the setup and execution.

## Dependencies

The CryptoCurrencyMonitor project has the following dependencies:

- Spring Boot 3.0.6
- Spring Web
- Spring Cache
- Spring Validation
- Spring Actuator
- Spring DevTools
- Gson
- Spring Data JPA
- PostgreSQL
- H2 Database
- Flyway Core
- Lombok
- Springdoc OpenAPI

## Getting Started

To run the CryptoCurrencyMonitor service locally, you have two options: running the application directly or running it as a Docker container.

### Running the Application Directly

To run the application directly, follow these steps:

1. Clone the GitHub repository.
2. Make sure you have Java 17 installed on your system.
3. Open the project in your preferred IDE.
4. Configure the database settings in the application properties file (application.yml or application.properties).
5. Build the project using Maven (_If you don't have Apache Maven installed, you can use wrapper script from root directory `./mvnw`_):

`mvn clean install`

6. Run the application using the provided Maven command:

`mvn spring-boot:run -Dspring.profiles.active=production`

7. Access the Swagger UI documentation and test the available REST endpoints at http://localhost:8080/swagger-ui/index.html.

### Running the Application as a Docker Container

To run the application as a Docker container, make sure you have Docker installed on your system. Then, follow these steps:

1. Clone the GitHub repository.
2. Open a terminal or command prompt and navigate to the project directory.
3. Build the Docker image using the following command:

`docker build -t cryptocurrency-monitor:latest .`

4. Run the Docker container using the following command:

`docker run -p 8081:8080 cryptocurrency-monitor:latest`

_This command maps port 8080 of the container to port 8081 on the host machine. Adjust the port mapping as needed._
5. Access the Swagger UI documentation and test the available REST endpoints by visiting http://localhost:8081/swagger-ui/index.html.

### Running the Application from Docker Hub

If you prefer to run the application directly from Docker Hub, follow these steps:

1. Make sure you have Docker installed on your system.
2. Open a terminal or command prompt.
3. Run the following command to pull the Docker image from Docker Hub:

`docker run -p 8081:8080 mikitaserou/cryptocurrency-watcher:latest`

4. This command pulls the **mikitaserou/cryptocurrency-watcher** image and runs it in a Docker container, mapping port 8080 of the container to port 8081 on the host machine. Adjust the port mapping as needed.
5. Access the Swagger UI documentation and test the available REST endpoints by visiting http://localhost:8081/swagger-ui/index.html.

## Conclusion

The CryptoCurrencyMonitor project provides a REST service for viewing cryptocurrency quotes. It is implemented using Java 17 and Spring Boot, and it allows users to fetch the latest prices for cryptocurrencies, register for price change notifications, and log warnings when significant price changes occur. The application can be run directly or as a Docker container, providing flexibility and ease of deployment.