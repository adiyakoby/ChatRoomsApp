package com.ex5adiyakobymichaelzargari.tabels;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ChatRoomRepository provides CRUD operations for chat rooms.
 */
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByName(String name);

    List<ChatRoom> findByEnabledFalse();

    List<ChatRoom> findByEnabledTrue();
}
