<%--
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
<p><a href="chat">Refresh Chat</a></p>

User: <input type="text" id="user" value="Anonymous"> </br></br>
Message: <textarea id="message" ></textarea>

</br></br>

<button onclick="postMessage()"> Send Message </button>
<button onclick="clearChat()"> Clear Chat </button>

</br></br>



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

        }).then(response => console.log(response));
    }

    function clearChat(){

        fetch(url, {
            method: "DELETE",
            body: JSON.stringify({}),
        }).then(response => alert(response))
        //.then(json => console.log(json));

    }

</script>

</body>
</html>
