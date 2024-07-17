<%-- 
    Document   : login
    Created on : Feb 25, 2024, 3:30:27 PM
    Author     : kienm
// chú ý tới name 


--%>

<%@page import="java.util.List"%>
<%@page import="LitJunction.book.BookDTO"%>
<%@page import="LitJunction.admin.AdminDTO"%>
<%@page import="LitJunction.customer.CustomerDTO"%>
<%@page import="LitJunction.category.CategoryDTO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./css/editcategory.css">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    </head>
    <body>
        
        <%List<CategoryDTO> listcate = (List<CategoryDTO>) request.getAttribute("cateList"); %>
        <%String actionC = (String) request.getAttribute("action"); %>
        
        <div class="textadmin">           
            <p>Welcome Admin: ${sessionScope.adminsession.admin_name}</p>
        </div>
        
        
        <div class="headeradmin">
            <%@ include file="/headeradmin.jsp" %>
        </div>
        
        <div>
            <h1>EDIT CATEGORY</h1>
        </div> 
        
        
        <div style="display: flex;
                    justify-content: center;">    
        <form action="./book" method="GET">
            <div id="room_fields">
                
                <% if(actionC.equals("updatecategory")){ %>
                
                <span>Category: <input type="text" name="category_id" value = "${requestScope.detailCate.category_id}"  placeholder="Category ID" required readonly/><input type="text" name="category_name" value = "${requestScope.detailCate.category_name}" placeholder="Category Name" required/></br></span>    

                <%}else{%>
                    <span>Category: <input type="text" name="category_id" value = "${requestScope.detailCate.category_id}"  placeholder="Category ID" required/><input type="text" name="category_name" value = "${requestScope.detailCate.category_name}" placeholder="Category Name" required/></br></span>    

                
                <%}%>
            </div>
                    
            <% if(actionC.equals("insertcategory")){ %>
                <input type="button" id="more_fields" onclick="addFields();" value="Add More Category" />
            <%}%>
            
                <input id="submit" type="submit" name = "action" value="${requestScope.action}">
                
        </form> 
   </div>
        
        
        
    <script>
        function addFields() {
    
            const cateid = document.createElement("input");
                cateid.type = "text";
                cateid.name = "category_id";
                cateid.placeholder="Category ID";
                //cateid.value = "";
            const catename = document.createElement("input");
                catename.type = "text";
                catename.name = "category_name";
                catename.placeholder="Category Name"
                //catename.value = "";
                
            const br = document.createElement("br");
    
            const newSpan = document.createElement("span");
                newSpan.innerHTML = `Category: `;
                newSpan.appendChild(cateid);
                newSpan.appendChild(catename);
                newSpan.appendChild(br);

            document.getElementById("room_fields").appendChild(newSpan);
        }
    </script>
                 
    
    </body>
</html>

