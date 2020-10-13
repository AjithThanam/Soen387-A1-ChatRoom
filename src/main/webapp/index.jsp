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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="index.css">
    <title>Chat App</title>
</head>

<body class="card">
<%--<p><a href="chat">Refresh Chat</a></p>--%>
<div id="chatBox" class="row">
    <div id="textBox" class="col-sm-9">
        <div class="card">
            <div id="title" class="card"><h1>Chat Messages</h1></div>
            <div class="messageBox">
                <ul class="messages">
                    <%
                        List<ChatMessage> messages = (List<ChatMessage>)request.getAttribute("messages");

                        for(ChatMessage mes: messages){
                    %>
                    <li> <%= mes.getMessage() %> : <%= mes.getUsername() %></li>
                    <%
                        }
                    %>

                </ul>
            </div>
                <div class="input-group ">
                    <input type="text" class="form-control" placeholder="Type a message..." id="message">
                    <div class="input-group-append">
                        <button id="sendButton" type="button" class="btn btn-primary" onclick="postMessage()">Send</button>
                    </div>
                </div>
    </div>
</div>
    <div id="infoBox" class="col">
        <div class="card">
            <div class="card-body>">
                </br>
                User: <input type="text" id="user" value="Anonymous"> </br></br></br>
                Filter the list of messages </br></br>
                Start Date : <input type="datetime-local" id="start-time" name="meeting-time" value="2020-10-11T00:00" min="2020-01-01T00:00"> </br> </br>
                End Date: <input type="datetime-local" id="end-time" name="meeting-time" value="2020-10-11T00:00" min="2020-01-01T00:00"> </br>
                </br>
                <button id="reloadChat" onclick="reloadChat()"> Filter Messages </button>
                </br> </br> </br> </br></br> </br> </br> </br></br> </br> </br> </br></br> </br> </br> </br></br> </br> </br> </br></br> </br> </br>
                <button id="clearChat" onclick="clearChat()" > Clear Chat </button>
            </div>
        </div>
    </div>
</div>

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
        getDates();
        location.reload();
        fetch(url, {
            method: "POST",
            headers: {
                "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
            },
            body: data

        }).then(response => console.log(response));
    }

    function clearChat(){

        fetch(url, {
            method: "DELETE",
            body: JSON.stringify({}),
        }).then(response => console.log(response))

    }

    function reloadChat(){
        const toStr = encodeURI("qwe");
        const fromStr = encodeURI("qew");
        const format = encodeURI("xml");

        var filterUrl = new URL(url);
        filterUrl.searchParams.append("to", toStr);
        filterUrl.searchParams.append("from", fromStr);
        filterUrl.searchParams.append("format", format);

        window.location.href = filterUrl;
    }

    function getDates(){
        var startTime = document.getElementById("start-time").value
        console.log(startTime);
    }

</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>
