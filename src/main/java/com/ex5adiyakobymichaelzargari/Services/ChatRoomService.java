package com.ex5adiyakobymichaelzargari.Services;

import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(String name, String description) {
        if (chatRoomRepository.findByName(name) != null) {
            return null;
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.setDescription(description);
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom getChatRoomById(Long id) {
        return chatRoomRepository.findById(id).orElse(null);
    }
}
