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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="index-light.css" id="theme">
    <title>Chat App</title>
</head>

<body id="body" class="card" onload="setup()">
<%--<p><a href="chat">Refresh Chat</a></p>--%>
<div id="chatBox" class="row">
    <div id="textBox" class="col-sm-9">
        <div id="leftBox" class="card">

            <div id="title" class="card"><h1>Chat Messages</h1></div>

            <div class="messageBox">
                <div id="serverResponse"></div>


                <%
                    List<ChatMessage> messages = (List<ChatMessage>)request.getAttribute("messages");
                    int listSize = messages.size();
                    if(listSize == 0){
                %>
                <h4>No Message Found</h4>
                <%
                }
                else{
                %>
                <div>
                    <%
                        for(ChatMessage mes: messages){
                            System.out.println(mes);
                    %>

                    <div id="messagesText" class="card">
                        <h6 class="username" ><%= mes.getUsername() %> - <%= mes.getDatetime().withSecond(0).withNano(0) %> <img id="avatar" src="images/avatar.png"></h6>
                        <h4 class="message"><%= mes.getMessage() %> </h4>
                    </div></br>

                    <%
                        }
                    %>
                </div>

                <% } %>

            </div>
            <div class="input-group ">
                <input type="text" class="form-control" placeholder="Type a message..." id="message">
                <div class="input-group-append">
                    <button id="sendButton" type="button" onclick="postMessage()">Send</button>
                </div>
            </div>
        </div>
    </div>
    <div id="infoBox" class="col">
        <div id="rightBox" class="card">
            <div class="card-body">

                <h4 class="textColor">Dark/Light mode</h4>
                <button id="toggleMode" onclick="toggleColor()"> Toggle Appearance </button>
                </br></br>

                <h4 class="textColor">Set a username</h4>
                <label class="textColor">Username</label>
                <input type="text" id="user" value="Anonymous" class="form-control" style="width: 50%">
                </br>
                <button id="resetUser" onclick="resetUser()"> Reset User </button>
                </br></br></br>

                <h4 class="textColor">Filter the list of messages</h4>

                <label class="textColor">Start Date</label></br>
                <input type="datetime-local" id="start-time" name="meeting-time" value="2020-10-11T00:00"
                       min="2020-01-01T00:00"> </br> </br>

                <label class="textColor">End Date</label></br>
                <input type="datetime-local" id="end-time" name="meeting-time" value="2020-10-11T00:00"
                       min="2020-01-01T00:00"> </br></br>

                <button id="reloadChat" onclick="reloadChat()"> Filter Messages</button>
                </br> </br>

                <h4 class="textColor">Clear chat history?</h4>
                <h6 class="textColor">(Consider downloading the chat before clearing)</h6>
                <button id="clearChat" onclick="clearAllChat()"> Clear Chat</button>

                </br> </br> </br>
                <h4 class="textColor">Download current chat history</h4>
                <button id="loadText" onclick="downloadText()"> Download Text Format</button>
                <button id="loadXML" onclick="downloadXML()"> Download XML Format</button>
            </div>
        </div>
    </div>
</div>

<script>

    var url = 'http://localhost:8080/Soen387_A1_ChatRoom_war_exploded/chat'

    function postMessage() {

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

        }).then(response => goHome());

    }

    function toggleColor(){
        if (document.getElementById('theme').href != "http://localhost:8080/Soen387_A1_ChatRoom_war_exploded/index-light.css") {
            document.getElementById('theme').href = "index-light.css";
        } else {
            document.getElementById('theme').href = "index-dark.css";
        }
    }

    function goHome() {
        window.location.href = url
    }

    function clearChat() {

        const toStr = encodeURI(document.getElementById("start-time").value);
        const fromStr = encodeURI(document.getElementById("end-time").value);

        deleteMessageHelper(toStr, fromStr);
    }

    function clearAllChat() {
        deleteMessageHelper(null, null);
    }

    function deleteMessageHelper(toStr, fromStr) {
        var delUrl = new URL(url);

        if (toStr != null & fromStr != null) {
            delUrl.searchParams.append("to", toStr);
            delUrl.searchParams.append("from", fromStr);
        }

        fetch(delUrl, {
            method: "DELETE",
        }).then(response =>{
            goHome()
        })
    }

    function reloadChat() {

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

    function setup(){
        loadUsernameFromStore();
        attachListenToUsernameBox();
    }

    function attachListenToUsernameBox(){
        document.getElementById("user").onblur=saveUsernameToStore;
    }

    function resetUser(){
        document.getElementById("user").value = 'Anonymous';
        saveUsernameToStore();
    }

    function saveUsernameToStore(){
        const username = document.getElementById("user").value;
        localStorage.setItem('username', username);
    }

    function loadUsernameFromStore(){
        const username = getValueFromCookie("username");

        if(username != null && username.length != 0) {
            document.getElementById("user").value = username;
        }
        else
            document.getElementById("user").value = 'Anonymous';
    }

    function getValueFromCookie(key){
        const value = localStorage.getItem(key);
        return value;
    }

</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>
