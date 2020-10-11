package lib.chatroom.models;

import java.time.LocalDateTime;

public class ChatMessage {

    private String message;
    private String username;
    private LocalDateTime datetime;

    public ChatMessage(String mes, String user, LocalDateTime date){

        this.message = mes;
        this.username = user;
        //this.datetime = LocalDateTime.now();
        this.datetime = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
