<%-- 
    Document   : bodyhomepage
    Created on : Mar 19, 2024, 3:16:52 AM
    Author     : LENOVO
--%>


<%@page import="LitJunction.book.BookDTO"%>
<%@page import="LitJunction.customer.CustomerDTO"%>
<%@page import="LitJunction.category.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="./css/dropdowncate.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        input:focus::placeholder {
            color: red;
        }
        input:focus{
            color: red;
        }

    </style>
</head>
<body>
    <div>
        <%            
            List<CategoryDTO> listcate = (List<CategoryDTO>) request.getAttribute("cateList");
            CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("customersession");
            pageContext.setAttribute("customerDTO", customerDTO);
        %>

        <% List<BookDTO> list = (List<BookDTO>) request.getAttribute("listbook");%>
        

        <div class="container mt-2">
            <div class="row">

                <div class="col-md-6">
                    <div class="dropdown">
                        <button onclick="myFunction()" class="dropbtn">Category</button>
                        <div id="myDropdown" class="dropdown-content">
                            <% for (int i = 0; i < listcate.size(); i++) {
                            pageContext.setAttribute("listcate", listcate); 
                                %>
                            <a href="customer?action=list&catechoose=<%= listcate.get(i).getCategory_id()%>">
                                <%= listcate.get(i).getCategory_name()%>
                            </a>
                            <% }%>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <form action="./customer" method="GET" class="mt-3">
                        <div class="input-group">
                            <input name="keyword" type="text" class="form-control" placeholder="Search" value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-primary">Search</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>



        

        <div class="container mt-5">
            <div class="row">
                <% 
                    if (list != null) {
                    for (BookDTO book : list) {
                    pageContext.setAttribute("book", book);
                %>
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <a href="customer?action=detail&id=<%= book.getBook_id()%>"><img src="<%= book.getBook_image()%>" class="card-img-top equal-height-img" alt="<%= book.getTitle()%>"></a>
                        <div class="card-body">
                            <a href="customer?action=detail&id=<%= book.getBook_id()%>"><h5 class="card-title"><%= book.getTitle()%></h5></a>
                            <p class="card-text">Price: <%= book.getPrice()%> VNĐ</p>
                            <a href="customer?action=detail&id=<%= book.getBook_id()%>" class="btn btn-primary">View Details</a>
                            <button type="button" class="btn btn-success" onclick="buy('<%= book.getBook_id()%>')">Buy now</button>
                        </div>
                    </div>
                </div>
                <% } }%>
            </div>
        </div>

        

    </div>
</body>
</html>
