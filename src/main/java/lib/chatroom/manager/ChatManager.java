package lib.chatroom.manager;

import lib.chatroom.models.ChatMessage;
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
        ChatMessage mesObj = new ChatMessage(message, username, LocalDateTime.now());
        chatroom.addMessage(mesObj);
    }

    @Override
    public List<ChatMessage> listMessages(LocalDateTime start, LocalDateTime end) {
        return chatroom.getMessages(start, end);
    }

    @Override
    public void clearChat() {
        chatroom.clearMessages();
    }

}
