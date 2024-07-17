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
        
        <%List<SupplierDTO> listSupplier = (List<SupplierDTO>) request.getAttribute("supplierlist"); %>
        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>
        
        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>
        
        <div>
            <h1>LIST SUPPLIER</h1>
        </div>    
        
        <div colspan="6">
            
            <form action="./supplier" method=GET> 
                <input name=keyword type=text value="<%=request.getParameter("keyword")!=null?request.getParameter("keyword"):""%>">
                <input type=submit value=Search >
            </form>
                
            <div class="div_tb">    
            <table border="0.5" cellspacing="0" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Name</th>  
                    <th>Address</th> 
                    <th>Button</th> 
                </tr>
             
                    <%
                        for (SupplierDTO supplier : listSupplier){
                            pageContext.setAttribute("supplier", supplier);
                    %>
                
                <tr>
                    <td>${supplier.supplier_id}</td>
                    <td>${supplier.supplier_name}</td>
                    <td>${supplier.supplier_address}</td>
                    <td> 
                    <form action="./supplier"  method="GET">
                        <input type=hidden name="supplier_id" value="${supplier.supplier_id}">
                        <button id="submit" type="submit" value = "editsupplier" name = "action">EDIT </button>
                        <button id="submit" type="submit" value = "deletesupplier" name = "action">DELETE </button>
                        
                    </form>
                </td>
                 </tr>
                    <% 
                        }
                    %>
            </table>
            </div>    
                <form action="./supplier"  method="GET">
                    <button id="submit" type="submit" value = "createsupplier" name = "action"> CREATE SUPPLIER </button>
                </form>
           
        </div>
 
    </body>
</html>

