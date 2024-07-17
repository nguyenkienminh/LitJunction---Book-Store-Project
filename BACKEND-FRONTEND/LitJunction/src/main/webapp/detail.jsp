<%-- 
    Document   : login
    Created on : Feb 25, 2024, 3:30:27 PM
    Author     : kienm
// chú ý tới name 
<a href="../java/LitJunction/book/BookDTO.java"></a>


--%>

<%@page import="java.util.List"%>
<%@page import="LitJunction.book.BookDTO"%>
<%@page import="LitJunction.admin.AdminDTO"%>
<%@page import="LitJunction.customer.CustomerDTO"%>
<%@page import="LitJunction.category.CategoryDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./css/detailadmin.css">

<!DOCTYPE html>
<html lang="en">
    
    <body>
        
        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>
        
        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>
        
        <div>
            <h1>Product Detail</h1>
        </div>
         

    <div class="product-detail">
        <div class="img-detail">
            <img src="${requestScope.detail.book_image}" alt="Book Image">
        </div>
        
        <div class="product-info" cellspacing="0" cellpadding="5">
            
                
                 <table>
                <tr>
                    <td>Book ID</td>
                    <td>${requestScope.detail.book_id}</td>
                </tr>
                <tr>
                    <td>Title</td>
                    <td>${requestScope.detail.title}</td>
                </tr>
                <tr>
                    <td>Author</td>
                    <td>${requestScope.detail.author}</td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td>${requestScope.detail.price} (vnđ)</td>
                </tr>
                <tr>
                    <td>Describe</td>
                    <td>${requestScope.detail.describe}</td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td>${requestScope.detail.bookstore_quantity}</td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td>${requestScope.detailCate.category_name}</td>
                </tr>
                <tr>
                    <td>Supplier Name</td>
                    <td>${requestScope.detailsupp.supplier_name}</td>
                </tr>
                <tr>
                    <td>Supplier Address</td>
                    <td>${requestScope.detailsupp.supplier_address}</td>
                </tr>
            </table>
            
        </div>
    </div>
         
        
    </body>
</html>

