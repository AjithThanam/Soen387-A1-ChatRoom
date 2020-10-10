package lib.chatroom.manager;

import java.time.LocalDateTime;
import java.util.List;

public class ChatManager implements IChatManager {
    @Override
    public void postMessage(String username, String message) {

    }

    @Override
    public List<ChatManager> listMessage(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public void clearChat() {

    }
}
