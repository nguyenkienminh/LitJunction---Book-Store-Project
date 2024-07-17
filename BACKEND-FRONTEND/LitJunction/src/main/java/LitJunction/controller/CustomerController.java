/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.controller;

import LitJunction.book.BookDAO;
import LitJunction.book.BookDTO;
import LitJunction.category.CategoryDAO;
import LitJunction.category.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import LitJunction.customer.CustomerDAO;
import LitJunction.customer.CustomerDTO;
import LitJunction.invoice.InvoiceDAO;
import LitJunction.invoice.InvoiceDTO;
import LitJunction.invoicedetail.InvoicedetailDAO;
import LitJunction.invoicedetail.InvoicedetailDTO;
import LitJunction.supplier.SupplierDAO;
import LitJunction.supplier.SupplierDTO;
import java.util.List;
import javax.servlet.http.HttpSession;


public class CustomerController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        String keyword = request.getParameter("keyword");
        String cateC = request.getParameter("catechoose");
        
        BookDAO bookDAO = new BookDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        SupplierDAO SupplierDAO = new SupplierDAO();
        CustomerDAO  customerDAO = new CustomerDAO();
        
        List<BookDTO> listbook =null;
        
        HttpSession session = request.getSession(false);
        CustomerDTO currentCustomer = null;
        String namecus = null;
        List<CategoryDTO> listcate = null;
        float tOneInvoiceCus = 0.0f;
        
        List<BookDTO> listb = bookDAO.getAllBookCustomer();
            // phan trang cho book
            int page, numperpage = 9;
            int size = listb.size();
            int num = (size%numperpage==0?(size/numperpage):(size/numperpage)+1);
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page*numperpage, size);
            List<BookDTO> listPerPage = bookDAO.getListByPage(listb, start, end);
            
        
        if (session != null){       
            currentCustomer = (CustomerDTO) session.getAttribute("customersession");
            namecus = (String) session.getAttribute("name");
        }
        
        
        if (action == null || action.equals("list")){
            
                
            
                if(keyword != null && !keyword.trim().isEmpty()){
                    listbook = (List<BookDTO>) bookDAO.listCus(keyword, cateC);
                    
                    
                    request.setAttribute("listbook", listbook);
                } else if(cateC != null && !cateC.trim().isEmpty()){
                    listbook = (List<BookDTO>) bookDAO.listCus(keyword, cateC);
                    request.setAttribute("listbook", listbook);
                } else if (keyword == null && cateC == null){
                    request.setAttribute("listbook", listPerPage);
                    
                }
                
                String category = "";     
                listcate = (List<CategoryDTO>) categoryDAO.listCate(category.trim());
                
                
                request.setAttribute("cateList", listcate);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                RequestDispatcher rd = request.getRequestDispatcher("viewbookcustomer.jsp");  // trang book cua customer
                rd.forward(request, response);
            }
        
        
            
        if(action != null && action.equals("detail")){
                String id = request.getParameter("id");
                
                if (id != null){
                    BookDTO bookdto = bookDAO.detail(id);
                    
                    String category = Integer.toString(bookdto.getCategory_id());
                    String supplier = Integer.toString(bookdto.getSupplier_id());
                    
                    SupplierDTO suppdto = SupplierDAO.detail(supplier);
                    CategoryDTO catedto = categoryDAO.detail(category);
                    
                    request.setAttribute("detailsupp", suppdto);
                    request.setAttribute("detail", bookdto);
                    request.setAttribute("detailCate", catedto);
                    RequestDispatcher rd = request.getRequestDispatcher("detailBuyBook.jsp");  // trang book detail cua customer khac voi trang book detial cua admin (nho them trang detail_customer co nut mua hang)
                    rd.forward(request, response);
                }        
        }

        if (action.equals("invoice")){
                String idcustomer = request.getParameter("idcus");
                Integer id_cus = null;
                try {
                    id_cus = Integer.parseInt(idcustomer);
                } catch (Exception ex) {
                    System.out.println("Error parseInt!" + ex.getMessage());
                }
                
                
                
                InvoiceDAO invoiceDAO = new InvoiceDAO();
                List<InvoiceDTO> listinvoicecus = invoiceDAO.getAllInvoiceByIdCusDesc(id_cus);
                request.setAttribute("listinvoicecus", listinvoicecus);
                RequestDispatcher rd = request.getRequestDispatcher("invoicecustomer.jsp");  // trang book cua customer
                rd.forward(request, response);
            }
        
        else if (action.equals("InvoiceDetailCus")) {
                
                String invoice_id_raw = request.getParameter("invoice_id");
                Integer invoice_id = null;
                try {
                    invoice_id = Integer.parseInt(invoice_id_raw);
                } catch (Exception ex) {
                    System.out.println("Error parseInt!" + ex.getMessage());
                }
                
                
                
                InvoicedetailDAO invoiceDetailDAO = new InvoicedetailDAO();
                List<InvoicedetailDTO> listInvoiceDetailCus = invoiceDetailDAO.getInvoiceDetailById(invoice_id);   
                
                for (int i = 0; i < listInvoiceDetailCus.size(); i++) {
                if(listInvoiceDetailCus.get(i).getInvoice_id() == invoice_id)
                    tOneInvoiceCus += (float) listInvoiceDetailCus.get(i).getBuy_quantity() * listInvoiceDetailCus.get(i).getPrice();
                }
                
                request.setAttribute("listInvoiceDetailCus", listInvoiceDetailCus);
                request.setAttribute("tOneInvoiceCus", tOneInvoiceCus);
                RequestDispatcher rd = request.getRequestDispatcher("invoicedetailcustomer.jsp"); 
                rd.forward(request, response);
            }
        
        if(action.equals("detailcustomer")){
            
            String idcustomer = request.getParameter("idcus");
            CustomerDTO object = null;
                    
            if(idcustomer != null){
                 object = customerDAO.detailCustomer(idcustomer);
            }
            
            currentCustomer.setCustomer_id(object.getCustomer_id());
            currentCustomer.setCustomer_name(object.getCustomer_name());
            currentCustomer.setCustomer_address(object.getCustomer_address());
            currentCustomer.setPhonenumber(object.getPhonenumber());
            currentCustomer.setPassword(object.getPassword());
            currentCustomer.setCurrent_points(object.getCurrent_points());
            currentCustomer.setUsername(object.getUsername());
            currentCustomer.setPassword(object.getPassword());
            currentCustomer.setAdmin_id(object.getAdmin_id());
            
            request.setAttribute("object", object);
            RequestDispatcher rd = request.getRequestDispatcher("detailcustomer.jsp");  // trang book detail
            rd.forward(request, response);
            
        }
        
        if(action.equals("createcustomer")){
            
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            
            CustomerDTO cuscurr = new CustomerDTO();
            cuscurr.setCustomer_name(request.getParameter("fullname"));
            cuscurr.setCustomer_address("");
            cuscurr.setPhonenumber("");
            cuscurr.setUsername(request.getParameter("username"));
            cuscurr.setPassword(request.getParameter("password"));
            cuscurr.setGender(request.getParameter("gender"));
            cuscurr.setStatuses("active");
            
            if(customerDAO.checkUPCus(username) ==  false){
                request.setAttribute("error", "The username of account is existed");
                RequestDispatcher rd = request.getRequestDispatcher("register_customer.jsp"); 
                rd.forward(request, response);
            } else if (customerDAO.checkUPCus(username) ==  true){
            
                    boolean check = true;
                    int increase = 1;
                    int pull = 0;
                    int kid = 0;
                    while(check == true){
                            pull = customerDAO.getIDCustomer(increase);

                            boolean checkID = customerDAO.checkIDCustomer(pull);
                
                            if(checkID == true){
                               
                                if(pull + increase == 1){
                                    kid = 1;
                                }
                                kid = pull + increase;
                                cuscurr.setCustomer_id(kid);
                                check = false;
                            } else if(checkID == false){
                                check = true;
                                increase += 1;
                            }
                    }
            
                    customerDAO.insert(cuscurr);
                    request.setAttribute("words", "The account has register "
                            + "Please Login!!");
                    RequestDispatcher rd = request.getRequestDispatcher("login_customer.jsp"); 
                    rd.forward(request, response);
            }
        }
        
        if(action.equals("editcustomer")){
            String id = request.getParameter("customer_id");
                
                CustomerDTO customerDTO = null;
                
                if(id != null){
                    customerDTO = customerDAO.detailCustomer(id);   
                }

                request.setAttribute("cussedit", customerDTO);
                request.setAttribute("action", "updatecustomer");
                RequestDispatcher rd = request.getRequestDispatcher("editCustomer.jsp");
                rd.forward(request, response);
        }
        
        
        if(action.equals("updatecustomer")){
            
            String password = request.getParameter("password").trim();
            String username = request.getParameter("username").trim();
            String idC = request.getParameter("customer_id").trim();
            CustomerDTO cusupdate = new CustomerDTO();
            
            cusupdate.setCustomer_id(currentCustomer.getCustomer_id());
            cusupdate.setCustomer_name(request.getParameter("customer_name").trim());
            cusupdate.setCustomer_address(request.getParameter("customer_address").trim());
            cusupdate.setPhonenumber(request.getParameter("phonenumber").trim());
            cusupdate.setUsername(request.getParameter("username").trim());
            cusupdate.setPassword(request.getParameter("password").trim());
            cusupdate.setGender(request.getParameter("gender").trim());
            cusupdate.setStatuses("active");
            
            if(password.equals("") && username.equals(currentCustomer.getUsername())){
                cusupdate.setCustomer_address(request.getParameter("customer_address").trim());
                cusupdate.setPhonenumber(request.getParameter("phonenumber").trim());
                cusupdate.setUsername(currentCustomer.getUsername());
                cusupdate.setPassword(currentCustomer.getPassword());
                cusupdate.setStatuses("active");
                customerDAO.update(cusupdate);    
                
                CustomerDTO after = customerDAO.detailCustomer(idC);
                currentCustomer.setCustomer_name(after.getCustomer_name());
                currentCustomer.setCustomer_address(after.getCustomer_address());
                currentCustomer.setCurrent_points(after.getCurrent_points());
                currentCustomer.setPhonenumber(after.getPhonenumber());
                currentCustomer.setGender(after.getGender());
                currentCustomer.setUsername(after.getUsername());
                currentCustomer.setPassword(after.getPassword());
                
                
                request.setAttribute("object", after);
                request.setAttribute("error", "Update Successfully");
                RequestDispatcher rd = request.getRequestDispatcher("detailcustomer.jsp"); 
                rd.forward(request, response);
                
            } else if(!password.equals("") || !username.equals(currentCustomer.getUsername())){
                request.setAttribute("objectupdateC", cusupdate);
                request.setAttribute("action", "confirmupdatecustomer");
                RequestDispatcher rd = request.getRequestDispatcher("ConfirmCustomer.jsp");
                rd.forward(request, response);   
            }
  
        }
        
        
        if(action.equals("confirmupdatecustomer")){
            
            String passwordCheck = request.getParameter("passwordCheck").trim();
            String usernameCheck = request.getParameter("usernameCheck").trim();
            String idCheck = request.getParameter("idcheck").trim();
            
            
            boolean tuple = customerDAO.checkChnage(usernameCheck, passwordCheck, idCheck);
            
            if(tuple == true){
                
                    CustomerDTO cusupdate = new CustomerDTO();
                    
                    cusupdate.setCustomer_id(Integer.parseInt(request.getParameter("idcheck").trim()));
                    cusupdate.setCustomer_name(request.getParameter("usernameU").trim());
                    cusupdate.setCustomer_address(request.getParameter("addressu").trim());
                    cusupdate.setPhonenumber(request.getParameter("phonenumberu").trim());
                    cusupdate.setUsername(request.getParameter("usernameU").trim());
                    cusupdate.setStatuses("active");
                    if(request.getParameter("passwordU").trim().equals("")){
                        cusupdate.setPassword(currentCustomer.getPassword());
                    }else{
                        cusupdate.setPassword(request.getParameter("passwordU").trim());
                    }
                    
                    cusupdate.setGender(request.getParameter("genderU").trim());
                    
                    customerDAO.update(cusupdate);
                    CustomerDTO after = customerDAO.detailCustomer(idCheck);
                    
                    currentCustomer.setCustomer_name(after.getCustomer_name());
                    currentCustomer.setCustomer_address(after.getCustomer_address());
                    currentCustomer.setCurrent_points(after.getCurrent_points());
                    currentCustomer.setPhonenumber(after.getPhonenumber());
                    currentCustomer.setGender(after.getGender());
                    currentCustomer.setUsername(after.getUsername());
                    currentCustomer.setPassword(after.getPassword());
                    currentCustomer.setStatuses(after.getStatuses());
                            
                    request.setAttribute("object", after);
                    request.setAttribute("error", "Update Successfully");
                    RequestDispatcher rd = request.getRequestDispatcher("detailcustomer.jsp"); 
                    rd.forward(request, response);
                
                } else{
                    CustomerDTO customerDTO = customerDAO.detailCustomer(idCheck);   
                    request.setAttribute("cussedit", customerDTO);
                    request.setAttribute("action", "updatecustomer");
                    request.setAttribute("error", "Enter a wrong password Can not change");
                    RequestDispatcher rd = request.getRequestDispatcher("editCustomer.jsp");
                    rd.forward(request, response);
                }
        }
        
        if(action.equals("deletecustomer")){
            
            String confirm= request.getParameter("confirmdelete").trim();
            String customer_id = request.getParameter("customer_id").trim();
            
            if(confirm.equals("yes")){
                if(customer_id != null){
                    currentCustomer = customerDAO.detailCustomer(customer_id);

                    customerDAO.updatefordelete(currentCustomer, customer_id);
                    
                    request.setAttribute("actionD", "logout");
                    RequestDispatcher rd = request.getRequestDispatcher("logincustomer");
                    rd.forward(request, response);
                }
            }else if(confirm.equals("no")){
                
                currentCustomer = customerDAO.detailCustomer(customer_id);
                request.setAttribute("error", "Enter a wrong password Can not change");
                session.setAttribute("customersession", currentCustomer);
                RequestDispatcher rd = request.getRequestDispatcher("detailcustomer.jsp");
                rd.forward(request, response);
            }
        
        }
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}


