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
        <div>
            <%@ include file="/header-customer.jsp" %>
        </div>
        
        <title>Show Profile</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .popup {
            position: relative;
            
        }

        .popup .popuptext {
            display: none;
            position: absolute;
            z-index: 1;
            bottom: 125%;
            left: 50%;
            background-color: #f9f9f9;
            min-width: 120px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            padding: 10px;
            border-radius: 5px;
            transform: translateX(-50%);
        }

        .popup .popuptext::after {
            content: "";
            position: absolute;
            top: 100%;
            left: 50%;
            margin-left: -5px;
            border-width: 5px;
            border-style: solid;
            border-color: #f9f9f9 transparent transparent transparent;
        }

        .popup .show {
            display: block;
        }
        </style>
    </head>

    
    
    <body>
        
        
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
        
        
        <div class="container mt-5">
        <div>
            <h1>Customer Detail</h1>
        </div>
            
        <div> 
            <table class="table">
                <tr>
                    <th>Customer ID</th>
                    <td>${sessionScope.customersession.customer_id}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td>${sessionScope.customersession.customer_name}</td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td>${sessionScope.customersession.customer_address}</td>
                </tr>
                <tr>
                    <th>Phone Number</th>
                    <td>${sessionScope.customersession.phonenumber}</td>
                </tr>
                <tr>
                    <th>Current Points</th>
                    <td>${sessionScope.customersession.current_points}</td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td>${sessionScope.customersession.gender}</td>
                </tr>                
            </table>           
        </div>
                
    <form action="./customer"  method="GET">
        <input name ="customer_id" value ="${sessionScope.customersession.customer_id}" type="hidden" >
        <button id="submit" type="submit" value = "editcustomer" name = "action" class="btn btn-primary"> CHỈNH SỬA THÔNG TIN </button>
         
        <div class="popup" onclick="myFunction()">
            <button id="submit" type="button" class="btn btn-danger" > XÓA TÀI KHOẢN </button>
            
            <span class="popuptext" id="myPopup" style="color: red;
                                                        border: 2px solid black; " >
                    Bạn có chắc muốn xóa không?
                    <input type="hidden" name="action" id="action" value ="deletecustomer" />
                    <button onclick="deleteItem()" class="btn btn-danger" value = "yes" name = "confirmdelete" id = "confirmdelete"> Xóa</button>
                    <button onclick="closePopup()" value = "no" name = "confirmdelete" id = "confirmdelete"> Hủy</button>
                </span>
        </div>
                 
    </form>  
    </div>
        
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        
        <%@ include file="/footer.jsp" %>
        
        <script>
            function myFunction() {
                var popup = document.getElementById("myPopup");
                popup.classList.toggle("show");
            }

            function deleteItem() {
               var stat = document.getElementById("confirmdelete");
               var action = document.getElementById("action");
               stat.submit();
               action.submit();
            }

            function closePopup() {
                var stat = document.getElementById("confirmdelete");
                var action = document.getElementById("action");
                stat.submit();
                action.submit();
            }
        </script>     
        
    </body>
    
    

</html>

