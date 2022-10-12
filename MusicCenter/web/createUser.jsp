<%@page import="minhtl.users.UserDTO"%>
<%@page import="minhtl.users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
        <link rel="stylesheet" href="css/createUser.css" type="text/css">
    </head>
    <body>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
            UserDTO tmpUser = (UserDTO)request.getAttribute("TMP_INFO");
            if(tmpUser == null){
                tmpUser = new UserDTO();
            }
        %>
        <div class="signup">
            <a class="nonactive" href="login.jsp"> sign in </a>
            <a class="active"> sign up </a>
            <form action="MainController" method="POST">
                <input type="text" class="text" name="userName" required="" placeholder="Enter your name" value="<%=tmpUser.getUserName()%>"/>
                <br>
                <div class="error"><%= userError.getUserName()%></div>
                <span>full name</span>
                <br>
                <input type="text" class="text" name="userId" required="" placeholder="Choose a user ID" value="<%=tmpUser.getUserId()%>"/>
                <br>
                <div class="error"><%= userError.getUserId()%></div>
                <span>user id</span>
                <br>
                <input type="text" class="text" name="userRoleId" value="US" readonly=""/>
                <br>
                <span class="noError">role id</span><br>
                <input type="password" class="text" name="password" placeholder="Enter password"><br>
                <span class="noError">password</span>
                <br>
                <input type="password" class="text" name="confirm" placeholder="Re-enter password">
                <br>
                <div class="error"><%= userError.getConfirm()%></div>
                <span>confirm password</span>
                <input class="register" type="submit" name="action" value="Register"/>
                <div class="return"><a href="home.jsp">return homepage</a> </div>
            </form>
        </div>
    </body>
</html>
