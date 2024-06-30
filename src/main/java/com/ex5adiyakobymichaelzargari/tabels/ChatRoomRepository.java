package com.ex5adiyakobymichaelzargari.tabels;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByName(String name);
    List<Message> findMessageByName(String chatRoomName);
    List<Message> findMessagesById(Long chatRoomId);
    List<ChatRoom> findChatRoomByEnabled(boolean enabled);

    List<ChatRoom> findAllByOrderByNameAsc();

    List<ChatRoom> findByEnabledFalse();

    List<ChatRoom> findByEnabledTrue();
}
