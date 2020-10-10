package lib.chatroom.manager;

import lib.chatroom.models.Chatroom;

import java.time.LocalDateTime;
import java.util.List;

public class ChatManager implements IChatManager {

    private Chatroom chatroom;

    public ChatManager(){
        this.chatroom = new Chatroom();
    }

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
