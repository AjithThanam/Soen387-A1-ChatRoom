package web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "chat")
public class ChatServlet extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        System.out.println("WORKING");
        PrintWriter out = res.getWriter();
        out.println("Hello, world!");
        out.close();
    }

    //include

}
