<%-- 
    Document   : login
    Created on : Feb 25, 2024, 3:30:27 PM
    Author     : kienm
// chú ý tới name 

--%>

<%@page import="LitJunction.category.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="LitJunction.book.BookDTO"%>
<%@page import="LitJunction.admin.AdminDTO"%>
<%@page import="LitJunction.customer.CustomerDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./css/viewadmin.css">
<link rel="stylesheet" href="./css/dropdowncate.css">



<!DOCTYPE html>
<html lang="en">
    <head>
        
    </head>
    <body>
        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>

        <%
            List<CategoryDTO> listcate = (List<CategoryDTO>) request.getAttribute("cateList");
        %>

        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>

        <p></p>

        <div class="dropdown">
            <button onclick="myFunction()" class="dropbtn">Category</button>
            <div id="myDropdown" class="dropdown-content">

                <%
                    for (int i = 0; i < listcate.size(); i++) {
                        pageContext.setAttribute("listcate", listcate);
                %>
                <a href="book?action=list&catechoose=<%= listcate.get(i).getCategory_id()%>"><%= listcate.get(i).getCategory_name()%></a>

                <%
                    }
                %>

            </div>
        </div>




        <% List<BookDTO> list = (List<BookDTO>) request.getAttribute("listbook");%>
        <form action="./book" method=GET> 
            <input name=keyword type=text value="<%=request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>" placeholder="Nhập để tìm kiếm sản phẩm">
            <input type=submit value=Search>
            <span></form >
        
        <form style="margin-bottom: 0px;" action="./book" method=GET> 
            <input name="action" value="outofstock" type=hidden>
            <input type=submit value="Out Of Stock" >
        </form></span>



        <table border="0.5" cellspacing="0" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Image</th>
                <th>Price</th>
                <th>Button</th>
            </tr>

            <%
                for (BookDTO book : list) {
                    pageContext.setAttribute("book", book);
            %>        

            <tr>
                <td>${book.book_id}</td>

                <td>
                    <a href="book?action=detail&id=${book.book_id}" >
                        ${book.title}
                    </a>
                </td>

                <td>
                    <a href="book?action=detail&id=${book.book_id}" >
                        <img src = "${book.book_image}" width = "80" height="100"/>
                    </a>
                </td>

                <td>${book.price}</td>

                <td> 
                    <form action="./book"  method="GET">
                        <input type=hidden name="id" value="${book.book_id}">
                        <button id="submit" type="submit" value = "edit" name = "action">EDIT </button>
                        <button id="submit" type="submit" value = "delete" name = "action">DELETE </button>
                    </form>
                </td>
            </tr>

            <% }%>
        </table> 
        </br>
        <table cellspacing="0" cellpadding="5">
            <tr>
            <form action="./book"  method="GET">
                <button id="submit" type="submit" value = "create" name = "action"> CREATE BOOK </button>
            </form>
        </tr>
        
    </table>    

        
    <%
        int paping = (Integer) request.getAttribute("page");
        int num = (Integer) request.getAttribute("num");
    %>
    <div class="numofpage">
        <% for (int i = 1; i <= num; i++) {%>
        <a class="<%= i == paping ? "active" : ""%>" href="book?page=<%= i%>"><%= i%></a>
        <% }%>
    </div>

    <script>
        function myFunction() {
            document.getElementById("myDropdown").classList.toggle("show");
        }


        window.onclick = function (event) {
            if (!event.target.matches('.dropbtn')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                var i;
                for (i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }
    </script>

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
    
</body>
</html>

