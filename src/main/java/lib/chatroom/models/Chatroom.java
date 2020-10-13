package lib.chatroom.models;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;



public class Chatroom {

    private static int id;
    private List<ChatMessage> messages;

    /*
    public static void main(String[] args) {

//        Chatroom cr = new Chatroom();
//
//        Month month = Month.of(1);
//        Month month2 = Month.of(2);
//        Month month3 = Month.of(3);
//        Month month4 = Month.of(4);
//        Month month5 = Month.of(5);
//        Month month6 = Month.of(6);
//        Month month7 = Month.of(7);
//        Month month8 = Month.of(8);
//        Month month9 = Month.of(9);
//        Month month10 = Month.of(10);
//
//        LocalDateTime date = LocalDateTime.of(2020, month, 2, 3,12, 45, 45);
//        LocalDateTime date2 = LocalDateTime.of(2020, month2, 2, 3,12, 45, 45);
//        LocalDateTime date3 = LocalDateTime.of(2020, month3, 2, 3,12, 45, 45);
//        LocalDateTime date4 = LocalDateTime.of(2020, month4, 2, 3,12, 45, 45);
//        LocalDateTime date5 = LocalDateTime.of(2020, month5, 2, 3,12, 45, 45);
//        LocalDateTime date6 = LocalDateTime.of(2020, month6, 2, 3,12, 45, 45);
//        LocalDateTime date7 = LocalDateTime.of(2020, month7, 2, 3,12, 45, 45);
//        LocalDateTime date8 = LocalDateTime.of(2020, month8, 2, 3,12, 45, 45);
//        LocalDateTime date9 = LocalDateTime.of(2020, month9, 2, 3,12, 45, 45);
//        LocalDateTime date10 = LocalDateTime.of(2020, month10, 2, 3,12, 45, 45);
//
//        ChatMessage msg1 = new ChatMessage("hihihi", "17g2j4", date);
//        ChatMessage msg2 = new ChatMessage("hihihi", "17g2j4", date2);
//        ChatMessage msg3 = new ChatMessage("hihihi", "17g2j4", date3);
//        ChatMessage msg4 = new ChatMessage("hihihi", "17g2j4", date4);
//        ChatMessage msg5 = new ChatMessage("hihihi", "17g2j4", date5);
//        ChatMessage msg6 = new ChatMessage("hihihi", "17g2j4", date6);
//        ChatMessage msg7 = new ChatMessage("hihihi", "17g2j4", date7);
//        ChatMessage msg8 = new ChatMessage("hihihi", "17g2j4", date8);
//        ChatMessage msg9 = new ChatMessage("hihihi", "17g2j4", date9);
//        ChatMessage msg10 = new ChatMessage("hihihi", "17g2j4", date10);
//
//        messages.add(msg1);
//        messages.add(msg2);
//        messages.add(msg3);
//        messages.add(msg4);
//        messages.add(msg5);
//        messages.add(msg6);
//        messages.add(msg7);
//        messages.add(msg8);
//        messages.add(msg9);
//        messages.add(msg10);
//
//        for (ChatMessage temp : cr.getMessages(date2, date6)) {
//            System.out.println(temp);
//        }

    }

     */

    public Chatroom(){
        this.id = this.id + 1;
        this.messages = new ArrayList<>();
        loadmock();
    }

    public void loadmock(){

        Month month = Month.of(1);
        Month month2 = Month.of(2);
        Month month3 = Month.of(3);
        Month month4 = Month.of(4);
        Month month5 = Month.of(5);
        Month month6 = Month.of(6);
        Month month7 = Month.of(7);
        Month month8 = Month.of(8);
        Month month9 = Month.of(9);
        Month month10 = Month.of(10);

        LocalDateTime date = LocalDateTime.of(2020, month, 2, 3,12, 45, 45);
        LocalDateTime date2 = LocalDateTime.of(2020, month, 2, 3,12, 45, 45);
        LocalDateTime date3 = LocalDateTime.of(2020, month, 2, 3,12, 45, 45);
        LocalDateTime date4 = LocalDateTime.of(2020, month, 2, 3,12, 45, 45);
        LocalDateTime date5 = LocalDateTime.of(2020, month, 2, 3,12, 45, 45);
        LocalDateTime date6 = LocalDateTime.of(2020, month6, 2, 3,12, 45, 45);
        LocalDateTime date7 = LocalDateTime.of(2020, month6, 2, 3,12, 45, 45);
        LocalDateTime date8 = LocalDateTime.of(2020, month6, 2, 3,12, 45, 45);
        LocalDateTime date9 = LocalDateTime.of(2020, month6, 2, 3,12, 45, 45);
        LocalDateTime date10 = LocalDateTime.of(2020, month6, 2, 3,12, 45, 45);

        ChatMessage msg1 = new ChatMessage("Remove", "17g2j4", date);
        ChatMessage msg2 = new ChatMessage("Remove", "17g2j4", date2);
        ChatMessage msg3 = new ChatMessage("Remove", "17g2j4", date3);
        ChatMessage msg4 = new ChatMessage("Remove", "17g2j4", date4);
        ChatMessage msg5 = new ChatMessage("Remove", "17g2j4", date5);
        ChatMessage msg6 = new ChatMessage("Keep1", "17g2j4", date6);
        ChatMessage msg7 = new ChatMessage("Keep2", "17g2j4", date7);
        ChatMessage msg8 = new ChatMessage("Keep3", "17g2j4", date8);
        ChatMessage msg9 = new ChatMessage("Keep4", "17g2j4", date9);
        ChatMessage msg10 = new ChatMessage("Keep 5 (Need 5)", "17g2j4", date10);

        messages.add(msg1);
        messages.add(msg2);
        messages.add(msg3);
        messages.add(msg4);
        messages.add(msg5);
        messages.add(msg6);
        messages.add(msg7);
        messages.add(msg8);
        messages.add(msg9);
        messages.add(msg10);

    }

    public void addMessage(ChatMessage message){
        this.messages.add(message);
    }

    public List<ChatMessage> getMessages(LocalDateTime start, LocalDateTime end){

        if(start == null || end == null)
            return messages;
        else if(start.isAfter(end))
            return messages;

        int startIndex = -1;
        int endIndex = -1;

        for(int i = 0; i < messages.size(); i++){
            if(messages.get(i).getDatetime().isAfter(start)){
                startIndex = 0;
                break;
            }
            else if((!messages.get(i).getDatetime().isBefore(start)) || messages.get(i).getDatetime().isEqual(start)) {
                startIndex = i;
                break;
            }

        }

        for(int i = messages.size() - 1; i >= 0 ; i--){
            if(messages.get(i).getDatetime().isBefore(end)){
                endIndex = messages.size() - 1;
                break;
            }
            else if((!messages.get(i).getDatetime().isAfter(end)) || messages.get(i).getDatetime().isEqual(end)) {
                endIndex = i  ;
                break;
            }
        }

        if(startIndex == -1 || endIndex == -1 || startIndex == endIndex){
            ChatMessage error = new ChatMessage("No messages found within this range", "Error");
            ArrayList<ChatMessage> tempList = new ArrayList<ChatMessage>();
            tempList.add(error);

            return tempList;
        }


        return messages.subList(startIndex, endIndex);
    }

    public void clearAllMessages(){
        this.messages = new ArrayList<>();
    }

    public void clearMessages(LocalDateTime start, LocalDateTime end) throws Exception {

        //


        if (start.isBefore(end)) {
                int startIndex = -1;
                int endIndex = -1;

                for (int i = 0; i < messages.size(); i++) {
                    if (messages.get(i).getDatetime().isAfter(start)) {
                        startIndex = 0;
                        break;
                    } else if ((!messages.get(i).getDatetime().isBefore(start)) || messages.get(i).getDatetime().isEqual(start)) {
                        startIndex = i;
                        break;
                    }

                }

                for (int i = messages.size() - 1; i >= 0; i--) {
                    if (messages.get(i).getDatetime().isBefore(end)) {
                        endIndex = messages.size() - 1;
                        break;
                    } else if ((!messages.get(i).getDatetime().isAfter(end)) || messages.get(i).getDatetime().isEqual(end)) {
                        endIndex = i;
                        break;
                    }
                }

                if (startIndex != -1 && endIndex != -1) {
                    for (int i = startIndex; i <= endIndex; i++)
                        messages.remove(i);
                }
                else
                    throw new Exception("Nothing to delete within given dates");

            }

    }

}
