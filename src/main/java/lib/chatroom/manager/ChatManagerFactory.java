package lib.chatroom.manager;

//Ensure the ChatManager is a singleton
public class ChatManagerFactory {

    private static IChatManager chatManager;

    private ChatManagerFactory(){}

    public synchronized static IChatManager getInstance(){

        if (chatManager == null){
            chatManager = new ChatManager();
        }
        return chatManager;
    }
}
