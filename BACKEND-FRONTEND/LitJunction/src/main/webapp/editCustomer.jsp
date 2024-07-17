<%-- Document : login Created on : Feb 25, 2024, 3:30:27 PM Author : kienm //
chú ý tới name --%> 

<%@page import="java.util.List"%> 
<%@page import="LitJunction.book.BookDTO"%> 
<%@page import="LitJunction.admin.AdminDTO"%> 
<%@page import="LitJunction.customer.CustomerDTO"%> 
<%@page import="LitJunction.category.CategoryDTO"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./css/editcustomer.css" />

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Edit Profile</title>
    
    <style>
        
body {
  font-family: Arial, sans-serif;
  color: rgb(0, 0, 0);
  margin: 0;
  
}

form {
  width: 100%;
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #cbcbcb;
  background: #ffffff;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
  color: rgb(0, 0, 0);
}

tr {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
td {
  display: flex;
  padding: 10px;
}

input[type="text"] {
  width: 100%;
  padding: 10px;
  margin: 5px 0;
  border: 1px solid #cbcbcb;
  border-radius: 3px;
  box-sizing: border-box;
}

/* Table styling */
table {
  width: 70%;
  border-collapse: collapse;
}

.td:first-child {
  width: 30%; /* Adjust as needed */
  text-align: right;
}

/* Radio buttons */
input[type="radio"] {
  margin-right: 5px;
}

/* Submit button */
input[type="submit"] {
  display: block;
  background-color: rgb(41, 146, 224);
  color: white;
  padding: 10px 20px;
  margin: 20px auto;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
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

            </br>
            
    <div>
        <h1 style="font-size: 32px;
                   color: #333;
                   text-align: center;" >
            EDIT PROFILE</h1>
    </div>

    <p>${requestScope.error}</p>

    <div colspan="6">
      <form action="./customer" method="POST">
        <table>
          <tr>
            <td>Name</td>
            <td>
              <input
                name="customer_name"
                value="${requestScope.cussedit.customer_name}"
                type="text"
              />
            </td>
          </tr>

          <tr>
            <td>Address</td>
            <td>
              <input
                name="customer_address"
                value="${requestScope.cussedit.customer_address}"
                type="text"
              />
            </td>
          </tr>

          <tr>
            <td>Phone Number</td>
            <td>
              <input
                name="phonenumber"
                value="${requestScope.cussedit.phonenumber}"
                type="text"
              />
            </td>
          </tr>

          <tr>
            <td>Username</td>
            <td>
              <input
                name="username"
                value="${requestScope.cussedit.username}"
                type="text"
              />
            </td>
          </tr>

          <tr>
            <td>Password</td>
            <td><input name="password" value="" type="text" /></td>
          </tr>

          <tr>
            <td>
              Gender: 
              <div>
                <label for="gender"></label>
                <input
                  type="radio"
                  id="male"
                  name="gender"
                  value="male"
                  required
                />Male
                <input
                  type="radio"
                  id="female"
                  name="gender"
                  value="female"
                  required
                />Female
              </div>
            </td>
          </tr>
        </table>

        <input
          name="customer_id"
          value="${sessionScope.customersession.customer_id}"
          type="hidden"/>
        <input name="action" value="${requestScope.action}" type="hidden" />
        <input value="Save" type="submit" />
      </form>
    </div>
  </body>
</html>
