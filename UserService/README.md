# user-hotel-rating-springboot-microservices
This project is a microservices-based application developed using Spring Boot with three core service, Hotel, User and Rating

    User Service: Manages user-related operations and uses MySQL for data persistence.
    Hotel Service: Handles hotel-related operations with a PostgreSQL database.
    Rating Service: Stores and retrieves user ratings for hotels, using MongoDB for NoSQL storage.

The architecture includes:

    Service Discovery: Powered by Eureka Server for service registration and discovery, with each service acting as a Eureka Client.
    API Gateway: Facilitates routing, load balancing, and centralized entry point for all requests.
    Centralized Configuration: A Config Server integrated with GitHub for managing shared configuration properties across services.

The system is tested using Postman to validate the REST APIs of individual services and end-to-end functionality. Each service exposes endpoints via Spring Boot REST APIs, ensuring clear separation of concerns and scalability.
