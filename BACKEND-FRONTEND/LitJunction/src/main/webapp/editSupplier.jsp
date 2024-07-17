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
<link rel="stylesheet" href="./css/editsupplier.css">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        
    </head>
    <body>
        
        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>
        
        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>
        
        <div>
            <h1>EDIT SUPPLIER</h1>
        </div> 
        
        <div class="div_tb">
        <form action = "./supplier" method = "post">

             <table>
                 <tr><td>Id</td><td><input name = "supplier_id" value = "${requestScope.objectSupplier.supplier_id}" type="text" placeholder="supplier_id" readonly></td></tr>
                 
                 <tr><td>Name</td><td><input name = "supplier_name" value = "${requestScope.objectSupplier.supplier_name}" type="text" required></td></tr>
                 
                 <tr><td>Address</td><td><input name = "supplier_address" value = "${requestScope.objectSupplier.supplier_address}" type="text" required></td></tr>
                 
  
             </table>
                 </br>
                 <div style=" display: flex;
                              justify-content: center;
                              align-items: center; ">
                     
                 <input name = "action" value="${requestScope.action}" type = "hidden" >
                 <input width="80px" value = Save type = "submit" style=" width: 100px" >
                 </div>
         </form>     
         </div>   
          
            
        
        
    </body>
</html>

