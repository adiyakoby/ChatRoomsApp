# Chat Application

### [Project Presentation from Google Drive](https://drive.google.com/drive/folders/1Bm6LGDGoH5JreZatmhmDnYwUYJHW-f6U?usp=sharing)
### Adi Yakoby | 208773168 | adija@edu.hac.ac.il
### Michael Zargari | 208289082 | michaelza@edu.hac.ac.il

## Overview

This Spring Boot application is a chat system that allows users to create and manage chat rooms, send messages in real-time using WebSocket, and perform user management tasks. The application uses Spring Security for authentication and authorization, JPA/Hibernate for database interaction, and WebSocket for real-time messaging.

## Project Structure

The project consists of several components:

- **Controllers**:
    - `AdminController`: Manages administrative tasks such as user banning, chat room approval, and user interface for the administrative dashboard.
    - `ChatRestController`: Handles REST endpoints related to chat room operations such as creation, deletion, enabling, and disabling.
    - `ChatRoomController`: Controls user interaction with chat rooms, including viewing messages, joining chat rooms, and handling errors.
    - `MainController`: Manages the home page, login, signup, and error pages.
    - `MessageController`: Handles message sending and receiving via WebSocket within chat rooms.
    - `UserController`: Manages user-related operations like registration, login, and user interface for the user dashboard.

- **Entities**:
    - `User`: Represents application users with username, password, and role.
    - `ChatRoom`: Represents chat rooms with name, description, messages, and user associations.
    - `Message`: Represents messages sent within chat rooms.

- **Repositories**:
    - `UserRepository`: JPA repository for user entities.
    - `ChatRoomRepository`: JPA repository for chat room entities.
    - `MessageRepository`: JPA repository for message entities.

- **Principal**:
    - `MyUserPrincipal`: Custom principal object for storing user details during authentication.

- **Services**:
    - `UserService`: Handles user-related operations like registration, banning, unbanning, and managing user roles.
    - `ChatRoomService`: Manages chat room operations including creation, deletion, message handling, and user associations.
    - `SecurityService`: Provides security-related operations like user authentication, role checking, and password hashing.

- **Configuration**:
    - `SecurityJavaConfig`: Configures Spring Security for authentication, authorization, and error handling.
    - `WebSocketConfig`: Configures WebSocket message handling with STOMP for real-time messaging.

- **Utilities**:
    - `UserDataSession`: Session management utility for storing user-specific data during a session.

## Users

### Regular Users
- **Registration & Login**: Users can register and log in to the application.
- **Chat Room Interaction**: Users can create, join, and leave chat rooms.
- **Messaging**: Users can send and receive messages in real-time within chat rooms.

### Admin Users
- **Admin Dashboard**: Admins have access to a special dashboard for managing the application.
- **User Management**: Admins can ban, unban users, and manage user roles.
- **Chat Room Management**: Admins can approve, disapprove, enable, disable, and delete chat rooms.

## Website Capabilities
- **Real-Time Messaging**: Utilizes WebSocket for instant messaging.
- **Role-Based Access Control**: Uses Spring Security to enforce different capabilities for regular users and admins.
- **Database Interaction**: Uses JPA/Hibernate for seamless database operations.
- **Session Management**: Manages user-specific data across sessions.

## Setup Instructions

1. **Prerequisites**:
    - Java 22 or higher installed
    - Maven for building the project
    - MySQL or another compatible database server

2. **Database Configuration**:
    - Configure `application.properties` with your database settings (e.g., URL, username, password).
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/chat_app
    spring.datasource.username=root
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    ```

3. **Build the Project**:
    ```bash
    mvn clean install
    ```

4. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

5. **Access the Application**:
    - Open your web browser and go to `http://localhost:8080`.

## Technologies Used

- **Spring Boot**: For building the backend application.
- **Spring Security**: For authentication and authorization.
- **JPA/Hibernate**: For ORM and database interaction.
- **WebSocket**: For real-time communication.
- **Thymeleaf**: For server-side rendering of HTML.
- **MySQL**: As the database server.


[index file](doc/index.html)