package lib.chatroom.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Chatroom {

    private static int id;
    private List<ChatMessage> messages;

    public Chatroom(){
        this.id = this.id + 1;
        this.messages = new ArrayList<>();
    }

    public void addMessage(ChatMessage message){
        this.messages.add(message);
    }

    public List<ChatMessage> getMessages(LocalDateTime start, LocalDateTime end){
        return null;
    }


}
