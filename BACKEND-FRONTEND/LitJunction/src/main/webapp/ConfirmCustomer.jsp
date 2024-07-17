<%-- 
    Document   : login
    Created on : Feb 25, 2024, 3:30:27 PM
    Author     : kienm
// chú ý tới name 


--%>

<%@page import="java.util.List"%>
<%@page import="LitJunction.book.BookDTO"%>
<%@page import="LitJunction.admin.AdminDTO"%>
<%@page import="LitJunction.customer.CustomerDTO"%>
<%@page import="LitJunction.category.CategoryDTO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <style>
            button {
            background-color: #007bff;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        
        button:hover {
            background-color: #0056b3;
            color: white;
        }
        </style>
    </head>
    <body>
        
        <%@ include file="/header-customer.jsp" %>
               
         <%
                if (session.getAttribute("name") != null) {
            %>
            <div style="text-align: center;
                        font-family: Avant Garde, Avantgarde, Century Gothic, CenturyGothic, AppleGothic, sans-serif;
                        font-size: 25px;
                        height: 50px;
                        padding-top: 0px;
                        padding-bottom: 1px;
                        color: #e0dfdc;
                        background-color: #333;
                        letter-spacing: .1em;
                        text-shadow: 0 -1px 0 #fff, 0 1px 0 #2e2e2e, 0 2px 0 #2c2c2c, 0 3px 0 #2a2a2a, 0 4px 0 #282828, 0 5px 0 #262626, 0 6px 0 #242424, 0 7px 0 #222, 0 8px 0 #202020, 0 9px 0 #1e1e1e, 0 10px 0 #1c1c1c, 0 11px 0 #1a1a1a, 0 12px 0 #181818, 0 13px 0 #161616, 0 14px 0 #141414, 0 15px 0 #121212, 0 22px 30px rgba(0, 0, 0, 0.9);">
            <p>Welcome Customer: ${sessionScope.customersession.customer_name}</p>
            </div>
            <%
            }
            %>
 
        <div>
            <h1 style="font-size: 32px;
                       color: #333;
                       text-align: center;">
                ENTER YOUR PASSWORD AND USERNAME TO CHANGE</h1>
        </div> 
        
            </br>
            
            <div style="display: flex;
                        justify-content: center;" >   
    <div class="container" style="width: 300px;
                                  border: 2px solid black;
                                  display: flex;
                                  justify-content: center;
                                  padding-top: 10px;" >    
        <form action="./customer" method="GET">
            <table>
                <tr><td>Username: </td><td><input style="border: 1px solid black;" name = "usernameCheck" value = "" type="text" required></td></tr>
                
                <tr><td>Password: </td><td><input style="border: 1px solid black;" name = "passwordCheck" value = "" type="text" required></td></tr>
                
            </table>
            
            <input name = "genderU" value="${requestScope.objectupdateC.gender}" type = "hidden">
            <input name = "passwordU" value="${requestScope.objectupdateC.password}" type = "hidden">
            <input name = "usernameU" value="${requestScope.objectupdateC.username}" type = "hidden">
            <input name = "phonenumberu" value="${requestScope.objectupdateC.phonenumber}" type = "hidden">
            <input name = "customer_nameu" value="${requestScope.objectupdateC.customer_name}" type = "hidden">
            <input name = "addressu" value="${requestScope.objectupdateC.customer_address}" type = "hidden">
            
            <input name = "idcheck" value="${sessionScope.customersession.customer_id}" type = "hidden">
            <input name = "action" value="${requestScope.action}" type = "hidden" >
            <div style="display: flex;
                        justify-content: center;
                        padding-bottom: 0px;" >
                <button value = Save type = "submit">Save</button></div>
        </form> 
   </div>
        </div>
        
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    
                    <%@ include file="/footer.jsp" %>
    
                 
    
    </body>
</html>

