<%-- 
    Document   : invoicedetail
    Created on : Mar 14, 2024, 10:45:26 PM
    Author     : LENOVO
--%>

<%@page import="LitJunction.invoicedetail.InvoicedetailDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/invoicedetail.css">
        <title>Invoice Detail Page</title>
    </head>
    <body>
        
        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>
        
        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>
        
        <div>
            <h1>STATISTIC INVOICE DETAIL</h1>
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
                <% List<InvoicedetailDTO> list = (List<InvoicedetailDTO>) request.getAttribute("listInvoiceDetail"); %>
             
                    <%
                        for (InvoicedetailDTO invoiceDetail: list){
                            pageContext.setAttribute("invoiceDetail", invoiceDetail);
                             
                    %>
                
           
                <tr>
                    <td>${invoiceDetail.invoice_id}</td>
                    <td>${invoiceDetail.book_id}</td>
                    <td>${invoiceDetail.buy_quantity}</td>
                    <td>${invoiceDetail.price}</td>
                    <td>${invoiceDetail.price * invoiceDetail.buy_quantity}</td>
                </tr>
                    <% 
                        }
                    %>
            </table>
            </div>
        </div>
            <div class="total">
            <p>Total Invoice Amount: ${requestScope.tOneInvoice} VND</p>
            </div>
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
                    </br>
                    </br>
                    </br>
                    </br>
                    </br>
            
        
    </body>
</html>
