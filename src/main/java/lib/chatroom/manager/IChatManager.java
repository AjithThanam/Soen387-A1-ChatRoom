package lib.chatroom.manager;

import java.time.LocalDateTime;
import java.util.List;

public interface IChatManager {

    void postMessage(String username, String message);

    List<ChatManager> listMessage(LocalDateTime start, LocalDateTime end);

    void clearChat();
}
