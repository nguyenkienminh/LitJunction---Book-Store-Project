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


<!DOCTYPE html>
<html lang="en">
        
<head>
    
    <title>Product Detail</title>
        <div class="">
            <%@ include file="/header-customer.jsp" %>
        </div>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        
        .img-detail {
            text-align: center;
            margin-bottom: 20px;   
        }
        .product-detail img {
            width: 100%; 
            height: auto; 
            max-height: 300px; 
        }
        .product-detail table {
            width: 100%;
        }
    </style>
</head>
        

    <body>

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
        
            
        <div class="container mt-5">
        <div class="row">
            <div class="col-md-4">
                <div class="img-detail">
                    <img src="${requestScope.detail.book_image}" class="img-fluid" alt="Book Image">
                </div>
            </div>
            <div class="col-md-8">
                <table class="table product-detail">
                    <tbody>
                        <tr>
                            <th scope="row">Book ID</th>
                            <td>${requestScope.detail.book_id}</td>
                        </tr>
                        <tr>
                            <th scope="row">Title</th>
                            <td>${requestScope.detail.title}</td>
                        </tr>
                        <tr>
                            <th scope="row">Author</th>
                            <td>${requestScope.detail.author}</td>
                        </tr>
                        <tr>
                            <th scope="row">Price</th>
                            <td>${requestScope.detail.price} (VNĐ)</td>
                        </tr>
                        <tr>
                            <th scope="row">Description</th>
                            <td>${requestScope.detail.describe}</td>
                        </tr>
                        <tr>
                            <th scope="row">Quantity</th>
                            <td>${requestScope.detail.bookstore_quantity}</td>
                        </tr>
                        <tr>
                            <th scope="row">Category</th>
                            <td>${requestScope.detailCate.category_name}</td>
                        </tr>
                        <tr>
                            <th scope="row">Supplier Name</th>
                            <td>${requestScope.detailsupp.supplier_name}</td>
                        </tr>
                        <tr>
                            <th scope="row">Supplier Address</th>
                            <td>${requestScope.detailsupp.supplier_address}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    

        <div>       
            <form name="f" action="" method="POST" style="visibility: hidden">
                Enter the number of items to by:
                <input type="number" name="num" value="1"/>
            </form>

            <input class="btn btn-primary" type="submit" onclick="buy('${requestScope.detail.book_id}')" value="Buy Now" style="width: 100px;
                                                                                                        float: right;
                                                                                                        margin-right: 100px;
                                                                                                        margin-bottom: 50px;"/>
        </div> 


        <script type="text/javascript">
            function buy(id) {
                document.f.action = "buy?id=" + id;
                document.f.submit();
            }
        </script>
    

    </body>
    <%@ include file="/footer.jsp" %>
</html>


