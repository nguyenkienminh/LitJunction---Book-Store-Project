<%--
    Document   : shelf
    Created on : Mar 13, 2024, 6:42:49 PM
    Author     : LENOVO
--%>


<%@page import="LitJunction.addtocart.Item"%>
<%@page import="LitJunction.addtocart.Cart"%>
<%@page import="LitJunction.book.BookDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        
<!DOCTYPE html>
<html>
    
    <head>
     <%@ include file="/header-customer.jsp" %>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Customer Shelf</title>
     
    
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
        <h1 style="display: flex;
                   justify-content: center;">My Bag</h1>
    <table class="table table-bordered table-striped">
        <% Object o = session.getAttribute("cart");
        int t = 0;
        if (o != null && o instanceof Cart) {
            Cart cart = (Cart) o;
            for (Item item : cart.getItems()) {
                t++;
        %>
        <thead>
        <tr>
            <th>No</th>
            <th>Title</th>
            <th>Quantity</th>
            <th>Money</th>
            <th>Total Amount</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>

        <tr>
            <td><%= t%></td>
            <td><%= item.getBook().getTitle()%></td>
            <td>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-secondary"><a href="addtocart?num=-1&id=<%= item.getBook().getBook_id()%>" class="text-white">-</a></button>
                    <input type="text" class="form-control" value="<%= item.getQuantity()%>" readonly>
                    <button type="button" class="btn btn-secondary"><a href="addtocart?num=1&id=<%= item.getBook().getBook_id()%>" class="text-white">+</a></button>
                </div>
            </td>
            <td><%= String.format("%.1f", item.getPrice())%></td>
            <td><%= String.format("%.1f", item.getQuantity() * item.getPrice())%></td>
            <td>
                <form action="addtocart" method="POST">
                    <input type="hidden" name="id" value="<%= item.getBook().getBook_id()%>"/>
                    <input type="submit" class="btn btn-danger" value="Delete item"/>
                </form>
            </td>
        </tr>
        <% }
        } %>
        </tbody>
    </table>
        
        
        <form action="./checkbuy" method="POST" style="display: flex;
                                                       justify-content: center;">
        <div class="popup">
            <button style="width: 100px;" id="submit" type="button" class="btn btn-primary">PAY</button>
            <span class="popuptext" id="myPopup" style="width: 700px;">
                </br>
                Are you sure you want to buy?
                </br>
                Address: <input name="customer_address" value="${sessionScope.customersession.customer_address}" type="text" required class="form-control">
                </br>
                Phone Number: <input name="phonenumber" value="${sessionScope.customersession.phonenumber}" type="text" required class="form-control">
                <input type="hidden" name="invoiceid" value="" />
                <button onclick="BuyItem()" value="yes" name="checkbuy" id="checkbuy" class="btn btn-primary">PAY</button>
                <button onclick="closePopup()" value="no" name="checkbuy" id="checkbuy" class="btn btn-secondary">CANCEL</button>
            </span>
        </div>
    </form>                
</div>


    <script>
        window.onload = () => {
            document.getElementById("submit").onclick = function () {
                myFunction()
            };

            function myFunction() {
                document.getElementById("myPopup").classList.toggle("show");
            }
        }

        function BuyItem() {
            var stat = document.getElementById("checkbuy");
            stat.submit();
        }

        function closePopup() {
            var stat = document.getElementById("checkbuy");
            stat.submit();
        }

    </script>


    </br>
    </br>
    </br>
    </br>
    </br>


    <h2 style="float: right;
               margin-bottom: 50px;
               margin-right: 50px;" ><a href="customer"/>Continue To Shopping</h2>

    <div class ="">
        <%@ include file="/footer.jsp" %>
    </div>

</body>



</html>
