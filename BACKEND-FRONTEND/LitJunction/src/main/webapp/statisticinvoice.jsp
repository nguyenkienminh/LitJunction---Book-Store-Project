<%-- 
    Document   : statisticinvoice
    Created on : Mar 14, 2024, 7:53:58 AM
    Author     : LENOVO
--%>

<%@page import="java.util.List"%>
<%@page import="LitJunction.invoice.InvoiceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./css/statisticInvoice.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Statistic Invoice</title>
    </head>
    <body>
        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>
        
        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>
        
        <div>
            <h1>STATISTIC INVOICE</h1>
        </div> 
        
        <div class="canlendar">
            <%@ include file="/Calendar.jsp" %>
        </div>
        
        </br>
        
        <div colspan="6">
            
            <div class="div_tb">    
            <table border="1" cellspacing="0" cellpadding="5">
                <tr>
                    <th>No</th>
                    <th>Invoice ID</th>
                    <th>Date</th>  
                    <th>Total Amount (VND)</th>
                    <th>Customer ID</th>
                    <th>Status</th>
                    <th>Invoice Detail</t>
                     
                </tr>
                <% List<InvoiceDTO> listinvoice = (List<InvoiceDTO>) request.getAttribute("listinvoice"); %>
             
                    <%
                        int i = 1;
                        for (InvoiceDTO invoice: listinvoice){
                            pageContext.setAttribute("invoice", invoice);
                            
                             
                    %>
                
           
                <tr>
                    <td><%=i++%></td>
                    <td>${invoice.invoice_id}</td>
                    <td>${invoice.date}</td>
                    <td>${invoice.total_amount}</td>
                    <td>${invoice.customer_id}</td>
                    <td>${invoice.buy_status}</td>
                    <td>
                        <form action="statistic"  method="GET">
                            <input  type=hidden name="invoice_id" value="${invoice.invoice_id}">
                            <button id="submit" type="submit" value = "InvoiceDetail" name = "action"> Show Detail </button>
                            <button id="submit" type="submit" value = "delete" name = "action"> Delete </button>
                        </form>
                    </td>
                </tr>
                    <% 
                        }
                    %>
            </table>
            
            <div class="total">
                <p>Total amount: ${requestScope.total} VND</p>
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
    </body>
</html>
