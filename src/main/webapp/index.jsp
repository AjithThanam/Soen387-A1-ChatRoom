<%@ page import="lib.chatroom.models.ChatMessage" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 10/9/2020
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- This Page is run when you visit the base link -->
<html>
<head>
    <title>Chat App</title>
</head>
<body>

<h1>Demo APP</h1>
<%--<p><a href="chat">Refresh Chat</a></p>--%>

<h1> Chat Messages: </h1>
<ul>
    <%
        List<ChatMessage> messages = (List<ChatMessage>)request.getAttribute("messages");

        for(ChatMessage mes: messages){
            System.out.println(mes);
    %>
    <li> <%= mes.getMessage() %> : <%= mes.getUsername() %></li>
    <%
        }
    %>

</ul>


User: <input type="text" id="user" value="Anonymous"> </br></br>
Message: <textarea id="message" ></textarea>

</br></br>

<button onclick="postMessage()"> Send Message </button>
<button onclick="clearAllChat()"> Clear Chat </button>

</br></br>

Filter the list of messages </br></br>
Date 1 : <input type="datetime-local" id="start-time" name="meeting-time" value="2020-10-11T00:00" min="2020-01-01T00:00">
Date 2 : <input type="datetime-local" id="end-time" name="meeting-time" value="2020-10-11T00:00" min="2020-01-01T00:00">
<button onclick="reloadChat()"> Filter Messages </button>
<button onclick="clearChat()"> Clear Date Ranged Messages </button>


</br></br>

<button onclick="downloadText()"> Download Text Format </button>
<button onclick="downloadXML()"> Download XML Format </button>

<script>

    var url = 'http://localhost:8080/Soen387_A1_ChatRoom_war_exploded/chat'

    function postMessage(){

        var current_user = document.getElementById("user").value;
        var mess = document.getElementById("message").value;
        document.getElementById("message").value = "";

        //Construct x-www-form payload
        var data = [];
        data.push(encodeURIComponent("user") + "=" + encodeURIComponent(current_user));
        data.push(encodeURIComponent("message") + "=" + encodeURIComponent(mess));
        data = data.join("&");

        fetch(url, {
            method: "POST",
            headers: {
                "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
            },
            body: data

        }).then(response => location.reload());
    }

    function clearChat(){

        const toStr = encodeURI("qwe");
        const fromStr = encodeURI("qew");

        deleteMessageHelper(toStr, fromStr);
    }

    function clearAllChat(){
        deleteMessageHelper(null, null);
    }

    function deleteMessageHelper(toStr, fromStr){
        var delUrl = new URL(url);

        if(toStr != null & fromStr != null) {
            delUrl.searchParams.append("to", toStr);
            delUrl.searchParams.append("from", fromStr);
        }

        fetch(delUrl, {
            method: "DELETE",
        }).then(response => location.reload())
    }

    function reloadChat(){

        const toStr = encodeURI(document.getElementById("start-time").value);
        const fromStr = encodeURI(document.getElementById("end-time").value);

        var filterUrl = new URL(url);
        filterUrl.searchParams.append("to", toStr);
        filterUrl.searchParams.append("from", fromStr);

        window.location.href = filterUrl;
    }


    function downloadText() {
       // const toStr = encodeURI("qwe");
        //const fromStr = encodeURI("qew");
        const format = encodeURI("text");

        var filterUrl = new URL(url);
       // filterUrl.searchParams.append("to", toStr);
        //filterUrl.searchParams.append("from", fromStr);
        filterUrl.searchParams.append("format", format);

        window.location.href = filterUrl;
    }

    function downloadXML() {
        // const toStr = encodeURI("qwe");
        //const fromStr = encodeURI("qew");
        const format = encodeURI("xml");

        var filterUrl = new URL(url);
        // filterUrl.searchParams.append("to", toStr);
        //filterUrl.searchParams.append("from", fromStr);
        filterUrl.searchParams.append("format", format);

        window.location.href = filterUrl;
    }

</script>

</body>
</html>
