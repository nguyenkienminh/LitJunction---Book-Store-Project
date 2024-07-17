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
<link rel="stylesheet" href="./css/categorylist.css">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    </head>
    <body>
        
        <%List<CategoryDTO> listcate = (List<CategoryDTO>) request.getAttribute("cateList"); %>
        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>
        
        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>
        
        
        <div>
            <h1>LIST CATEGORY</h1>
        </div>    
        
        <p>${requestScope.status}<p>
        
        <div class="div_tb" colspan="6">
            <table border="0.5" cellspacing="0" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Name</th>  
                    <th>Button</th> 
                </tr>
             
                    <%
                        for (CategoryDTO category: listcate){
                        pageContext.setAttribute("category", category);
                    %>
                
                <tr>
                    <td>${category.category_id}</td>
                    <td>${category.category_name}</td>
                    <td> 
                    <form action="./book"  method="GET">
                        <input type=hidden name="category_id" value="${category.category_id}">
                        <button id="submit" type="submit" value = "editcategory" name = "action">EDIT </button>
                        <button id="submit" type="submit" value = "deletecategory" name = "action">DELETE </button>
                        
                    </form>
                </td>
                 </tr>
                    <% 
                        }
                    %>
            </table>
            </div>    
                <form class="createcategory" action="./book"  method="GET">
                    <button id="submit" type="submit" value = "createcategory" name = "action"> CREATE CATEGORY </button>
                </form>
           
        
    </body>
</html>

