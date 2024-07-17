<%-- 
    Document   : paysuccess
    Created on : Mar 19, 2024, 1:29:25 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Order Successfully</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        .container {
            padding: 20px;
            text-align: center;
            background-color: #fff; 
            border: 1px solid #ddd; 
            border-radius: 10px; 
            width: 60%; 
            margin: 50px auto; 
        }
        .success-icon {
            color: green;
            font-size: 48px;
            margin-bottom: 20px;
        }
        .success-message {
            color: green;
            font-weight: bold;
            font-size: 20px;
            margin-bottom: 20px;
        }
        .button {
            background-color: green;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
        }
        a{
            text-decoration: none;
            color:rgb(15, 74, 162);
        }
        a:hover{
            color:rgb(32, 94, 188);
        }
    </style>
</head>
<body>
    <div class="">
        <%@ include file="/header-customer.jsp" %>
    </div>
    <div class="container" style="width: 1000px;
                                  border: 2px solid black;">
        <div style="display: flex;
                    justify-content: center;">
            <img src="./images/icon-thanh-cong-300x300.png" alt="Icon đặt hàng thành công" width="50px" height="50px"></div>
        <h2 class="success-message">ĐẶT HÀNG THÀNH CÔNG</h2>
        <p>----------------------------------</p>
        <p>ĐƠN HÀNG CỦA QUÝ KHÁCH ĐÃ ĐƯỢC ĐẶT HÀNG THÀNH CÔNG</p>
        <p>Cảm ơn bạn đã đặt hàng của chúng tôi. Chúng tôi sẽ liên hệ thông báo giao hàng với bạn.</p>
        <a href="">Chi tiết đơn hàng</a>
        <br><br>
        <button class="button" onclick="window.location.href='/LitJunction/customer'">QUAY LẠI TRANG MUA HÀNG</button>
    </div>
    </br>
    </br>
    </br>
    </br>
    </br>
    </br>
    
    <%@ include file="/footer.jsp" %>
</body>
    

</html>