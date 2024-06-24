package com.ex5adiyakobymichaelzargari.tabels;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findByName(String name);
}
