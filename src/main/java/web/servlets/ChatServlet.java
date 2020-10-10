package web.servlets;

import lib.chatroom.manager.ChatManager;
import lib.chatroom.manager.ChatManagerFactory;
import lib.chatroom.manager.IChatManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        System.out.println("WORKING");
        PrintWriter out = res.getWriter();


        out.println("Hello, world!");
        out.close();
    }


    //Use to send message to clear chat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n");
    }

    //Use to write new message to server
    @Override
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException
    {
        String user = request.getParameter("user");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n");
    }


}
