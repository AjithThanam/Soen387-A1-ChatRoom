package lib.chatroom.models;


import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Chatroom {

    private static int id;
    private List<ChatMessage> messages;

//    public static void main(String[] args) {
//
//    }


    public Chatroom(){
        this.id = this.id + 1;
        this.messages = new ArrayList<>();
    }

    public void addMessage(ChatMessage message){
        this.messages.add(message);
    }

    public List<ChatMessage> getMessages(String start, String end){
        start = extractTimeStamp(start);
        end = extractTimeStamp(end);


        return null;
    }

    public void clearMessages(){
        this.messages = new ArrayList<>();
    }

    public String extractTimeStamp(String fullDate){
        return fullDate.substring(4,24);
    }



}
