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

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Home</title>
        <style>
            
            .equal-height-img {
                height: 550px; 
                object-fit: cover; 
            }
        </style>
    </head> 
    <body>
        
        <%@ include file="/header-customer.jsp" %>
        
        <div>

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
           


            </br>

            <form name="f" action="" method="POST" style="visibility: hidden">
                Enter the number of items to by:
                <input type="number" name="num" value="1"/>
            </form>

                
        <div class="body-home-page">
            <%@ include file="/bodyhomepage.jsp" %>
        </div>
            

            </br>
            </br>
            </br>
            
            <%
                int paping = (Integer) request.getAttribute("page");
                int num = (Integer) request.getAttribute("num");
            %>
            <div class="numofpage">
                <% for (int i = 1; i <= num; i++) {%>
                <a class="<%= i == paping ? "active" : ""%>" href="customer?page=<%= i%>"><%= i%></a>
                <% }%>
            </div>
            </br>
            </br>
            </br>
            
            <%@ include file="/footer.jsp" %>

            <script type="text/javascript">
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

                function buy(id) {
                    document.f.action = "buy?id=" + id;
                    document.f.submit();
                }

            </script>    
        </div>
            
    </body>
    
</html>

 
