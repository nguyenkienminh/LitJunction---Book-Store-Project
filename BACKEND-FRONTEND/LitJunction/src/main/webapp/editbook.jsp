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
<link rel="stylesheet" href="./css/editbook.css">


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
            <h1>EDIT BOOK</h1>
        </div> 
        
        <div class="div_tb">
        <form action = "./book" method = "post">

            <table style="width: 100%;" >
                 
                 <%
                    if (request.getAttribute("action").equals("update")) {
                %>
                        <tr><td>Book ID </td><td><input name = "book_Id" value = "${requestScope.bookafter.book_id}" type="text" required readonly></td></tr>
                <%
                    } else {
                %>
                        <tr><td>Book ID </td><td><input name = "book_Id" value = "${requestScope.bookafter.book_id}" type="text" required ></td></tr>    
                <%
                    }
                %>
                 
                 <tr><td>Title </td><td><input name = "Title" value = "${requestScope.bookafter.title}" type="text" required></td></tr>
                 
                 <tr><td>Book Image </td><td><input name = "Book_image" value = "${requestScope.bookafter.book_image}" type="text" required></td></tr>
                 
                 <tr><td>Author </td><td><input name = "Author" value = "${requestScope.bookafter.author}" type="text" required></td></tr>
                 
                 <tr><td>Price </td><td><input name = "Price" value = "${requestScope.bookafter.price}" type="text" required></td></tr>
                 <tr><td>Describe </td><td><input name = "Describe" value = "${requestScope.bookafter.describe}" type="text" required></td></tr>
                 <tr><td>Quantity </td><td><input name = "Quantity" value = "${requestScope.bookafter.bookstore_quantity}" type="text" required></td></tr>
                 <tr>
                     <td>Category Name </td>
                     <td>
                        <select name = "Category_id" >
                                <option value= "${requestScope.cateObject.category_id}" >${requestScope.cateObject.category_name}</option>
                                <%
                                    for(int i=0; i< listcate.size() ; i++) {
                                        pageContext.setAttribute("listcate", listcate);
                                %>
                                <option value= "<%= listcate.get(i).getCategory_id()%>"><%= listcate.get(i).getCategory_name()%></option>
                                <%} %>
                        </select>                   
                     </td>
                 </tr>
                 
                 <tr><td>Supplier Name </td><td><input name = "supplier_name" value = "${requestScope.suppObject.supplier_name}" type="text" required></td></tr>
                 <tr><td>Supplier Address </td><td><input name = "supplier_address" value = "${requestScope.suppObject.supplier_address}" type="text" required></td></tr>
             </table>
             </br>
                <div style="display: flex; 
                            justify-content: center; 
                            align-items: center; ">  
                    <input name = "action" value="${requestScope.action}" type = "hidden" >
                    <input value = Save type = "submit" style=" width: 100px"  >
                </div>
         </form>     
         </div>  
          
        
    </body>
</html>

