# Chat Application

### Adi Yakoby | 208773168 | adija@edu.hac.ac.il
### Michael Zargari | 208289082 | michaelza@edu.hac.ac.il

## Overview

This Spring Boot application is a chat system that allows users to create and manage chat rooms, send messages in real-time using WebSocket, and perform user management tasks. The application uses Spring Security for authentication and authorization, JPA/Hibernate for database interaction, and WebSocket for real-time messaging.

## Project Structure

The project consists of several components:

- **Controllers**:
    - `AdminController`: Manages administrative tasks such as user banning, chat room approval, and user interface for administrative dashboard.
    - `ChatRestController`: Handles REST endpoints related to chat room operations such as creation, deletion, enabling, and disabling.
    - `ChatRoomController`: Controls user interaction with chat rooms, including viewing messages, joining chat rooms, and handling errors.
    - `MainController`: Manages the home page, login, signup, and error pages.
    - `MessageController`: Handles message sending and receiving via websocket within chat rooms .
    - `UserController`: Manages user-related operations like registration, login, and user interface for user dashboard.
  
- **Entities**:
    - `User`: Represents application users with username, password, and role.
    - `ChatRoom`: Represents chat rooms with name, description, messages, and user associations.
    - `Message`: Represents messages sent within chat rooms.

- **Repositories**:
    - `UserRepository`: JPA repository for user entities.
    - `ChatRoomRepository`: JPA repository for chat room entities.
    - `MessageRepository`: JPA repository for message entities.

- **Principal**:
    - `MyUSerPrincipal`: Custom principal object for storing user details during authentication.

- **Services**:
    - `UserService`: Handles user-related operations like registration, banning, unbanning, and managing user roles.
    - `ChatRoomService`: Manages chat room operations including creation, deletion, message handling, and user associations.
    - `SecurityService`: Provides security-related operations like user authentication, role checking, and password hashing.
- **Configuration**:
    - `SecurityJavaConfig`: Configures Spring Security for authentication, authorization, and error handling.
    - `WebSocketConfig`: Configures WebSocket message handling with STOMP for real-time messaging.

- **Utilities**:
    - `UserDataSession`: Session management utility for storing user-specific data during a session.

## Setup Instructions

1. **Prerequisites**:
    - Java 22 or higher installed
    - Maven for building the project
    - MySQL or another compatible database server

2. **Database Configuration**:
    - Configure `application.properties` with your database settings (e.g., URL, username, password).

3. **Running the Application**:
    - Build the project using Maven: `mvn clean install`
    - Run the application: `mvn spring-boot:run` or run the main application class directly.

4. **Accessing the Application**:
    - Once running, access the application at `http://localhost:8080`

## Additional Notes
- Ensure to handle database migrations and updates as per your environment and needs.
- Customize security settings (`SecurityJavaConfig`) and WebSocket configurations (`WebSocketConfig`) as required by your application's security and performance requirements.


[index file](doc/index.html)