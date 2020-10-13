package lib.chatroom.manager;

import lib.chatroom.models.ChatMessage;

import java.time.LocalDateTime;
import java.util.List;

public interface IChatManager {

    void postMessage(String username, String message);

    List<ChatMessage> listMessages(LocalDateTime start, LocalDateTime end);

    void clearChat(LocalDateTime start, LocalDateTime end) throws Exception;
}
