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


    /**
     * General messages
     */
    public static final String SUCCESS_CHAT_FORM_SENT = "Your request for a new chat room has been submitted successfully and is pending approval.";
    public static final String SUCCESS_SIGNUP_MSG = "You have been registered successfully";
    public static final String ERR_SIGNUP_UNEXPECTED = "An unexpected error occurred while trying to sign you up. Please try again.";
    public static final String ERR_CHATROOM_NOT_FOUND = "Chat room not found or not approved yet.";
    public static final String ERR_CHATROOM_FULL = "Chat room is full. You cannot join.";
    public static final String ERR_DELETE_CHATROOM_UNEXPECTED = "An unexpected error occurred while trying to delete the chatroom. Please try again.";
    public static final String ERR_GENERAL = "An unexpected error occurred. Please try again.";

    /**
     * AdminController constants
     */
    public static final String ADMIN_ERR_MAIN_ADMIN_BAN = "Cannot ban the main admin.";
    public static final String ADMIN_SUCCESS_USER_BAN = "User has been banned.";
    public static final String ADMIN_SUCCESS_USER_UNBAN = "User has been unbanned.";
    public static final String ADMIN_SUCCESS_CHATROOM_APPROVE = "Chat room has been approved.";
    public static final String ADMIN_SUCCESS_CHATROOM_DISAPPROVE = "Chat room has been disapproved.";
    public static final String ADMIN_ERR_MAIN_CHAT_DELETE = "Cannot delete the main chat.";
    public static final String ADMIN_SUCCESS_CHATROOM_DELETE = "Chat room has been deleted.";
    public static final String ADMIN_SUCCESS_CHATROOM_ENABLE = "Chat room has been enabled.";
    public static final String ADMIN_ERR_MAIN_CHAT_DISABLE = "Cannot disable the main chat.";
    public static final String ADMIN_SUCCESS_CHATROOM_DISABLE = "Chat room has been disabled.";

}

