<%-- 
    Document   : header-customer
    Created on : Mar 19, 2024, 1:43:52 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header class="relative shadow-lg px-3 py-2">
  <nav class="flex justify-between">
    <div class="w-[130px] md:w-[200px] flex items-center">
        <a href="customer"><img src="./images/LitJunction-logo.png" alt="LOGO"></a>
    </div>
    <div class="flex items-center gap-4">
      <div class="navLinks duration-500 absolute md:static md:w-auto w-full md:h-auto h-[85vh] bg-white flex md:items-center gap-[1.5vw] top-[100%] left-[-100%] px-5 md:py-0 py-5 ">
        <ul class="flex md:flex-row flex-col md:items-center md:gap-[2vw] gap-8">
            
            
            
          <li class="relative max-w-fit pr-3 md:pr-0 py-1 after:bg-gradient-to-r from-[#2b68e0] to-[#e710ea]  after:absolute after:h-1 after:w-0 after:bottom-0 after:left-0 hover:after:w-full after:transition-all after:duration-300"><a href="customer">Home</a></li>
          
          <% Integer size = (Integer) request.getAttribute("size");

            %>
          <li class="relative max-w-fit pr-3 md:pr-0 py-1 after:bg-gradient-to-r from-[#2b68e0] to-[#e710ea]  after:absolute after:h-1 after:w-0 after:bottom-0 after:left-0 hover:after:w-full after:transition-all after:duration-300"><a href="shelf.jsp">Mybag(${sessionScope.size})</a></li>
          
          <li class="relative max-w-fit pr-3 md:pr-0 py-1 after:bg-gradient-to-r from-[#2b68e0] to-[#e710ea]  after:absolute after:h-1 after:w-0 after:bottom-0 after:left-0 hover:after:w-full after:transition-all after:duration-300"><a href="logincustomer?action=logout">Logout</a></li>
          <%
                if (session.getAttribute("name") != null) {
            %>
          <li class="relative max-w-fit pr-3 md:pr-0 py-1 after:bg-gradient-to-r from-[#2b68e0] to-[#e710ea]  after:absolute after:h-1 after:w-0 after:bottom-0 after:left-0 hover:after:w-full after:transition-all after:duration-300"><a href="customer?action=detailcustomer&idcus=${sessionScope.customersession.customer_id}">Show Profile</a></li>
          <li class="relative max-w-fit pr-3 md:pr-0 py-1 after:bg-gradient-to-r from-[#2b68e0] to-[#e710ea]  after:absolute after:h-1 after:w-0 after:bottom-0 after:left-0 hover:after:w-full after:transition-all after:duration-300"><a href="customer?action=invoice&idcus=${sessionScope.customersession.customer_id}">Invoice</a></li>
          <%
            }
            %>
          <li class="relative max-w-fit pr-3 md:pr-0 py-1 after:bg-gradient-to-r from-[#2b68e0] to-[#e710ea]  after:absolute after:h-1 after:w-0 after:bottom-0 after:left-0 hover:after:w-full after:transition-all after:duration-300"><a href="./register_customer.jsp">Register</a></li>
        </ul>
      </div>
      <div class="flex items-center gap-2">
          <button type="button" class="hover:bg-clip-text hover:text-transparent bg-gradient-to-br from-[#2b68e0] to-[#e710ea] border-solid border-2 border-[#5356e3]  font-bold text-white px-5 py-2 rounded-full "><a href="./login_customer.jsp">Login</a></button>
        <ion-icon name="menu" onclick="onMenuToggle(this)" class="text-[30px] cursor-pointer md:hidden"></ion-icon>
      </div>
    </div>
  </nav>
    
  <script src="https://cdn.tailwindcss.com"></script>
  <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
  <script>
function onMenuToggle(e) {
  const navlinks = document.querySelector(".navLinks");
  e.name = e.name === "menu" ? "close" : "menu";
  navlinks.classList.toggle("left-[0%]");
}
</script>
    
</header>

