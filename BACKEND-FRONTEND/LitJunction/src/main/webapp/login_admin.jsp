<%-- 
    Document   : login
    Created on : Feb 25, 2024, 3:30:27 PM
    Author     : kienm
// bắt bằng name    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập Admin</title>
    <link rel="stylesheet" href="./css/login.css" >
</head>

<body class="login_background animation-section animation-content">
    
    <ul class="circles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
    
    <div>
    <form class="form_login" action="./loginadmin" method="GET">
        <h1>Đăng nhập tài khoản Admin</h1> 
        
        <div>
            <label for="fullname">Tên đăng nhập:</label>
            <input type="text" id="fullname" name="username_A" placeholder="Tên đăng nhập" required>
        </div>
        <div>
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password_A" placeholder="Mật khẩu" required >
        </div>
        
        <%
        String error = (String) request.getAttribute("error_A");
            if (error != null) {%>
            <h3><%= error%></h3>
        <%
            }
        %>
        
        <input id="submit" type="submit" name = "action" value="login">
    </form>
    </div>
</body>
</html>

