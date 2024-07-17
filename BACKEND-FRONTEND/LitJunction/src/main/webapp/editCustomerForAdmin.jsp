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



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="./css/editsupplier.css">
    </head>
    <body>

        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>

        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>

        <div>
            <h1>EDIT CUSTOMER</h1>
        </div> 

        <p>${requestScope.error}</p>

        <div colspan="6">    
            <form action="./cvacon" method="POST">
                <table class="div_tb">
                    <tr><td>Name </td><td><input name = "customer_name" value = "${requestScope.cussedit.customer_name}" type="text" ></td></tr>

                    <tr><td>Address </td><td><input name = "customer_address" value = "${requestScope.cussedit.customer_address}" type="text" ></td></tr>

                    <tr><td>Phone Number </td><td><input name = "phonenumber" value = "${requestScope.cussedit.phonenumber}" type="text" ></td></tr>

                    <% if (request.getAttribute("action").equals("insertcustomer")) { %>
                    <tr><td>Username </td><td><input name = "username" value = "${requestScope.cussedit.username}" type="text" required></td></tr>
                    <tr><td>Password </td><td><input name = "password" value = "${requestScope.cussedit.password}" type="text" required></td></tr>
                            <%
                            } else {
                            %>
                    <tr><td>Username </td><td><input name = "username" value = "${requestScope.cussedit.username}" type="text" ></td></tr>
                    <tr><td>Password </td><td><input name = "password" value = "${requestScope.cussedit.password}" type="text" ></td></tr>
                            <%
                                }
                            %>    


                    <tr><td>Status</td>
                        <td>
                            <select name = "statuses" >

                                <%
                                    List<String> liststatus = (List<String>) request.getAttribute("liststatus");

                                    for (int i = 0; i < liststatus.size(); i++) {
                                        pageContext.setAttribute("liststatus", liststatus);
                                %>
                                <option value= "<%= liststatus.get(i)%>"><%= liststatus.get(i)%></option>

                                <%}%>
                            </select>                   
                        </td>
                    </tr>        

                    <tr><td>Gender:
                            <div>
                                <label for="gender"></label>
                                <input type="radio" id="male" name="gender" value="male" required >Male
                                <input type="radio" id="female" name="gender" value="female" required>Female
                            </div>
                        </td></tr>
                    
                </table>   
                            <div class="save_center" style=" display: flex;
                                                             justify-content: center;
                                                             align-items: center;">  
                    <input name = "customer_id" value="${requestScope.cussedit.customer_id}" type = "hidden">
                    <input name = "action" value="${requestScope.action}" type = "hidden" >
                    <input style="width: 80px;" value = Save type = "submit" >
                </div>
            </form > 
        </div>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>


    </body>
</html>

