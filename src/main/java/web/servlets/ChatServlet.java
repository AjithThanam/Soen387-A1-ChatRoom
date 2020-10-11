package web.servlets;

import lib.chatroom.manager.ChatManager;
import lib.chatroom.manager.ChatManagerFactory;
import lib.chatroom.manager.IChatManager;
import lib.chatroom.models.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "chat")
public class ChatServlet extends HttpServlet {

    private IChatManager chatManager;

    @Override
    public void init() {
        chatManager = ChatManagerFactory.getInstance();
    }

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //url example: /chat?to=val&from=val&format=xml

        String to = request.getParameter("to");
        String from = request.getParameter("from");
        String format = request.getParameter("format");

        List<ChatMessage> messages = null;
        if(to != null & from != null & format != null) {
            LocalDateTime toObj = convertDateStringToObj(to);
            LocalDateTime fromObj = convertDateStringToObj(from);
            messages = this.chatManager.listMessages(toObj, fromObj);
        }else
            messages = this.chatManager.listMessages(null, null);


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(tempResponse(messages));
        out.close();
    }

    private String tempResponse(List<ChatMessage> messages){
        StringBuilder response = new StringBuilder("");
        response.append("<ul>");

        for(ChatMessage mes: messages){
            response.append("<li>" + mes.getMessage() + ":" + mes.getUsername() + "</li>");
        }

        response.append("</ul>");
        return response.toString();
    }


    //Use to send message to clear chat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("user");
        String message = request.getParameter("message");

        this.chatManager.postMessage(user, message);
        sendResponse(response);
    }

    //Use to write new message to server
    @Override
    public void doDelete(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException
    {
        this.chatManager.clearChat();
        sendResponse(response);
    }

    private void sendResponse(HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<p> Successful </p>");
    }



    private LocalDateTime convertDateStringToObj(String dateStr){

        HashMap<String, Integer> months = new HashMap<>();
        months.put("Jan", 1);
        months.put("Feb", 2);
        months.put("Mar", 3);
        months.put("Apr", 4);
        months.put("May", 5);
        months.put("Jun", 6);
        months.put("Jul", 7);
        months.put("Aug", 8);
        months.put("Sep", 9);
        months.put("Oct", 10);
        months.put("Nov", 11);
        months.put("Dec", 12);

        int year = Integer.parseInt(dateStr.substring(11,15));
        Month month = Month.of(months.get(dateStr.substring(4,7)));
        int dayOfMonth = Integer.parseInt(dateStr.substring(8,10));
        int hour = Integer.parseInt(dateStr.substring(16,18));
        int minute = Integer.parseInt(dateStr.substring(19,21));
        int second = Integer.parseInt(dateStr.substring(22,24));
        LocalDateTime date = LocalDateTime.of(year, month,dayOfMonth,hour,minute,second);
        System.out.println(date.toString());

        return date;

    }

}
