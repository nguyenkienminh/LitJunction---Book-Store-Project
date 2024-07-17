<%-- 
    Document   : login
    Created on : Feb 25, 2024, 3:30:27 PM
    Author     : kienm
// chú ý tới name 


--%>

<%@page import="LitJunction.supplier.SupplierDTO"%>
<%@page import="java.util.List"%>
<%@page import="LitJunction.book.BookDTO"%>
<%@page import="LitJunction.admin.AdminDTO"%>
<%@page import="LitJunction.customer.CustomerDTO"%>
<%@page import="LitJunction.category.CategoryDTO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./css/supplierlist.css">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    </head>
    <body>
        
        <%List<CustomerDTO> listcuss = (List<CustomerDTO>) request.getAttribute("listbook"); %>
        
        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>
        
        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>
        
        <div>
            <h1>LIST CUSTOMER</h1>
        </div>    
        
        <div colspan="6">
            
            <form action="./cvacon" method=GET>
                <input name=keyword type=text value="<%=request.getParameter("keyword")!=null?request.getParameter("keyword"):""%>">
                <input name ="action" value="customerlist" type="hidden">
                <input type=submit value=Search >
            </form>
                
            <div class="div_tb">    
            <table border="0.5" cellspacing="0" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Name</th>  
                    <th>Address</th>
                    <th>Phone Number</th>
                    <th>Current Points</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Gender</th>
                    <th>Status</th>
                    <th>Button</th>
                    
                </tr>
             
                    <%
                        for (CustomerDTO cuss : listcuss){
                            pageContext.setAttribute("cuss", cuss);
                    %>
                
                <tr>
                    <td>${cuss.customer_id}</td>
                    <td>${cuss.customer_name}</td>
                    <td>${cuss.customer_address}</td>
                    <td>${cuss.phonenumber}</td>
                    <td>${cuss.current_points}</td>
                    <td>${cuss.username}</td>
                    <td>${cuss.password}</td>
                    <td>${cuss.gender}</td>
                    <td>${cuss.statuses}</td>
                    <td>
                        <form action="./cvacon"  method="POST">
                            <input type=hidden name="customer_id" value="${cuss.customer_id}">
                            <button id="submit" type="submit" value = "editcustomer" name = "action">EDIT </button>
                            <button id="submit" type="submit" value = "deletecustomer" name = "action">DELETE </button>
                        </form>
                    </td>
                    
                </tr>
                    <% 
                        }
                    %>
            </table>
            </div>    
                <form action="./cvacon"  method="GET">
                    <button id="submit" type="submit" value = "createcustomer" name = "action"> CREATE CUSTOMER </button>
                </form>
           
        </div>
 
    </body>
</html>

