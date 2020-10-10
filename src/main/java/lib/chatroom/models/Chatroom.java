package lib.chatroom.models;

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


}
