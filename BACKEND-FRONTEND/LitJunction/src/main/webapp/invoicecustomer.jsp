<%-- 
    Document   : invoicecustomer
    Created on : Mar 15, 2024, 12:24:06 AM
    Author     : LENOVO
--%>

<%@page import="LitJunction.invoice.InvoiceDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Invoice Customer</title>
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
        .invoice{
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
            padding: 10px;
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
        
        button {
            background-color: #007bff;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            color: white;
        }
        
        button:hover {
            background-color: red;
        }
        </style>
    </head>
    
    <body>
        
        <%@ include file="/header-customer.jsp" %>
        
        
        
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
        
        <div class="invoice" >
            <div>
                <h1>YOUR INVOICE</h1>
            </div>    

            <div colspan="6">

                <div class="div_tb">    
                <table border="1" cellspacing="0" cellpadding="5">
                    <tr>
                        <th>Invoice ID</th>
                        <th>Date</th>  
                        <th>Total Amount (VND)</th>
                        <th>Invoice Detail</t>

                    </tr>
                    <% List<InvoiceDTO> listinvoice = (List<InvoiceDTO>) request.getAttribute("listinvoicecus"); %>

                        <%
                            for (InvoiceDTO invoice: listinvoice){
                                pageContext.setAttribute("invoice", invoice);

                        %>


                    <tr>
                        <td>${invoice.invoice_id}</td>
                        <td>${invoice.date}</td>
                        <td>${invoice.total_amount}</td>
                        
                        <td>
                            <form action="customer"  method="GET">
                                <input type=hidden name="invoice_id" value="${invoice.invoice_id}">
                                <button style="background-color: #007bff;
                                               padding: 10px 20px;
                                               border: none;
                                               cursor: pointer;
                                               border-radius: 5px;
                                               color: white;" type="submit" value = "InvoiceDetailCus" name = "action"> Show Detail </button>                       
                            </form>
                        </td>
                    </tr>
                        <% 
                            }
                        %>
                </table>
                </div>
            </div>
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
