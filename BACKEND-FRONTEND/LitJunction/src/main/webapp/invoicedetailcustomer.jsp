<%-- 
    Document   : invoicedetailcustomer
    Created on : Mar 15, 2024, 12:51:14 AM
    Author     : LENOVO
--%>

<%@page import="LitJunction.invoicedetail.InvoicedetailDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice Detail Customer</title>



        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #ffffff;
                margin: 0;
                display: block;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .invoice_detail{
                margin: 48px 101px 0;
                padding: 0 15px;
            }

            div h1 {
                font-size: 32px;
                color: #333;
                text-align: center;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                padding: 14px;
                text-align: left;
                border: 1px solid #ddd;
            }

            th {
                background-color: #ffffff;
                font-weight: bold;
            }


            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #f1f1f1;
            }

        </style>

    </head>



    <body>

        <%@ include file="/header-customer.jsp" %>

        <div class="invoice_detail">
            <div>
                <h1>INVOICE DETAIL CUSTOMER</h1>
            </div>    

            <div colspan="6">

                <div class="div_tb">    
                    <table border="1" cellspacing="0" cellpadding="5">
                        <tr>
                            <th>Invoice ID</th>
                            <th>Book ID</th>  
                            <th>Buy Quantity</th>
                            <th>Price (VND)</th>
                            <th>Total (VND)</th>

                        </tr>
                        <% List<InvoicedetailDTO> list = (List<InvoicedetailDTO>) request.getAttribute("listInvoiceDetailCus"); %>

                        <%
                            for (InvoicedetailDTO invoiceDetail : list) {
                                pageContext.setAttribute("invoiceDetail", invoiceDetail);

                        %>


                        <tr>
                            <td>${invoiceDetail.invoice_id}</td>
                            <td>${invoiceDetail.title}</td>
                            <td>${invoiceDetail.buy_quantity}</td>
                            <td>${invoiceDetail.price}</td>
                            <td>${invoiceDetail.price * invoiceDetail.buy_quantity}</td>
                        </tr>
                        <%                            }
                        %>
                    </table>
                    </br>
                    </br>
                    
                    <h2 style="font-size: 32px;
                               float: right;">Total: ${requestScope.tOneInvoiceCus} VNĐ</h2>
                </div>
            </div> 
        </div>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
                    <%@ include file="/footer.jsp" %>
    </body>
    
</html>
