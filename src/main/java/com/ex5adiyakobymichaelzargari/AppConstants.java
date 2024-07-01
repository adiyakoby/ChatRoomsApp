package com.ex5adiyakobymichaelzargari;

public class AppConstants {

    /**
     * Web-Socket constants
     */
    public static final String SOCKET_ERROR_PATH = "/topic/errors/";
    public static final String SOCKET_CHAT_PATH = "/topic/chatroom/";
    public static final String SOCKET_ERR_MSG_DIS_REV = "This chatroom was disabled or removed.";
    public static final String SOCKET_ERR_MSG_GENERAL = "This chatroom was disabled or removed.";
    public static final String SOCKET_ERR_MSG_USER_BANNED = "You were banned, therefore you cannot send messages.";


    /**
     * Roles constants
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_BANNED = "ROLE_BANNED";

    /**
     * User constants
     */
    public static final String USER_ERR_USERNAME_EMPTY = "username cannot be empty.";
    public static final String USER_ERR_PASSWORD_EMPTY = "password cannot be empty.";
    public static final String USER_ERR_PASSWORD_LENGTH = "Password must be at least 6 characters long.";

    /**
     * Message constants
     */
    public static final String MSG_ERR_EMPTY = "message is mandatory field.";


    /**
     * ChatRoom constants
     */
    public static final String CHAT_ERR_NAME_EMPTY = "ChatRoom name is mandatory field.";


    /**
     * UserService constants
     */
    public static final String USER_SRV_ERR_USERNAME_TAKEN = "Username already exists.";
    public static final String USER_SRV_ERR_NOT_FOUND = "User not found.";
    public static final String USER_SRV_ERR_CANT_RMV = "Can't remove Home Chat room from user";
    public static final String USER_SRV_ERR_INVALID_ID = "Invalid user ID";


    /**
     * ChatService constants
     */
    public static final String CHAT_SRV_ERR_ALRDY_EXIST = "Chat room name already exists";
    public static final String CHAT_SRV_ERR_INVALID_ID = "Invalid chat room ID";
    public static final String CHAT_SRV_ERR_NOT_FOUND = "Chat Room not found";




}

