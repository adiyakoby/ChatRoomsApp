package com.example.ex5adiyakobymichaelzargari.tabels;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {

    List<ChatRoom> getAllChatRooms();
    List<ChatRoom> getChatRooms(String roomName);
    List<Message> getAllMessages(String roomName);
    ChatRoom getChatRoom(String roomName);



//
//    void addChatRoom(ChatRoom chatRoom);
//    void removeChatRoom(ChatRoom chatRoom);
//    void addMessage(Message message);
//    void removeMessage(Message message);
}
