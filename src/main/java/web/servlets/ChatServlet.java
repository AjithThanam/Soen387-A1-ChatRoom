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
import java.util.List;

@WebServlet(name = "chat")
public class ChatServlet extends HttpServlet {

    private IChatManager chatManager;

    @Override
    public void init() {
        chatManager = ChatManagerFactory.getInstance();
    }

    @Override
    public void doGet (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        //url example: /chat?to=val&from=val&format=xml

        String to = req.getParameter("to");
        String from = req.getParameter("from");
        String format = req.getParameter("format");

        //LocalDate = toObj = null;
        //LocalDate = fromObj = null;

        List<ChatMessage> messages = this.chatManager.listMessages(null, null);


        //System.out.println("WORKING");
        PrintWriter out = res.getWriter();


        out.println("Hello, world!");
        out.close();
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
        writer.append("<p> Success </p>");
    }

    private void convertDateStringToObj(String dateStr){

        //return dateObject
    }

}
