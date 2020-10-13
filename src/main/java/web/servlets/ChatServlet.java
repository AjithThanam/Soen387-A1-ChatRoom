package web.servlets;

import lib.chatroom.manager.ChatManagerFactory;
import lib.chatroom.manager.IChatManager;
import lib.chatroom.models.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "chat")
public class ChatServlet extends HttpServlet {

    private IChatManager chatManager;
    private static String successResponse;

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
        if(to != null & from != null) {
            LocalDateTime toObj = convertDateStringToObj(to);
            LocalDateTime fromObj = convertDateStringToObj(from);
            messages = this.chatManager.listMessages(toObj, fromObj);
        }else
            messages = this.chatManager.listMessages(null, null);

        if(format != null)
            if(format.equals("text")) {
                try {
                    FileWriter textFileWriter  = new FileWriter(request.getServletContext().getRealPath("")
                            + "/WEB-INF/chat.txt");

                    for(ChatMessage message: messages) {
                        textFileWriter.write(message.getUsername() + ": " + message.getMessage() + " (" +
                                message.getDatetime().toString().replace('T', ' ') + ")"
                                + System.lineSeparator());
                    }
                    textFileWriter.close();

                } catch (IOException e) {
                    System.out.println("Error creating text file.");
                }

                response.setContentType("text/plain");
                response.setHeader("Content-disposition", "attachment; filename=chat.txt");

                try(InputStream inputStream = request.getServletContext().getResourceAsStream("/WEB-INF/chat.txt");
                    OutputStream outputStream = response.getOutputStream()) {

                    byte[] buffer = new byte[1024];

                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) > -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }else
                if(format.equals("xml")){
                    try {
                        FileWriter xmlFileWriter  = new FileWriter(request.getServletContext().getRealPath("")
                                + "/WEB-INF/chat.xml");

                        writeToXMLFile(xmlFileWriter, messages);

                    } catch (IOException e) {
                        System.out.println("Error creating xml file.");
                    }

                    response.setContentType("text/xml");
                    response.setHeader("Content-disposition", "attachment; filename=chat.xml");

                    try(InputStream inputStream = request.getServletContext().getResourceAsStream("/WEB-INF/chat.xml");
                        OutputStream outputStream = response.getOutputStream()) {

                        byte[] buffer = new byte[1024];

                        int bytesRead = -1;
                        while ((bytesRead = inputStream.read(buffer)) > -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }

        //send jsp page
        String jspPath = "index.jsp";
        request.setAttribute("messages", messages);
        request.getRequestDispatcher(jspPath).forward(request, response);

    }

    private String tempResponse(List<ChatMessage> messages){
        StringBuilder response = new StringBuilder("<h1> Here are your messages </h1>");
        response.append("<ul>");

        for(ChatMessage mes: messages){
            response.append("<li>" + mes.getUsername() + ":" + mes.getMessage() +  ":" +mes.getDatetime().toString() + "</li>");
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
        successResponse = "Success";
        sendResponse(response);
    }

    //Use to write new message to server
    @Override
    public void doDelete(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException
    {
        String toStr = request.getParameter("to");
        String fromStr = request.getParameter("from");

        LocalDateTime to = convertDateStringToObj(toStr);
        LocalDateTime from = convertDateStringToObj(fromStr);

        try {
            this.chatManager.clearChat(to, from);
            sendResponse(response);
        }catch (Exception e){
            successResponse = e.getMessage();
            sendResponse(response);
        }

    }

    private void sendResponse(HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<p> "+ successResponse + " </p>");
    }

    private LocalDateTime convertDateStringToObj(String dateStr){

        try {
            dateStr = dateStr.replace('T', ' ');
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
            return date;
        }catch(Exception e){
            return null;
        }
    }

    private void writeToXMLFile(FileWriter fileWriter, List<ChatMessage> messages) throws IOException {
        fileWriter.write("<chat>" + System.lineSeparator());
        for(ChatMessage message: messages) {
            fileWriter.write("    <chatmessage>" + System.lineSeparator());
            fileWriter.write("        <message>" +  message.getMessage() + "</message>" + System.lineSeparator());
            fileWriter.write("        <username>" +  message.getUsername() + "</username>" + System.lineSeparator());
            fileWriter.write("        <date>" +  message.getDatetime().toString().replace('T', ' ') + "</date>" + System.lineSeparator());
            fileWriter.write("    </chatmessage>" + System.lineSeparator());
        }
        fileWriter.write("</chat>" + System.lineSeparator());
        fileWriter.close();
    }

}
