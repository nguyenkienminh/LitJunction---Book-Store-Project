/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.controller;

import LitJunction.admin.AdminDAO;
import LitJunction.admin.AdminDTO;
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
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.servlet.http.HttpSession;
import LitJunction.supplier.SupplierDAO;
import LitJunction.supplier.SupplierDTO;


public class CustomerAdminController extends HttpServlet {

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
            String image_set = request.getParameter("image_set");
            String cateC = request.getParameter("catechoose");
            
            CustomerDAO customerDAO = new CustomerDAO();
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            
            HttpSession session = request.getSession(false);
            AdminDTO currentAdmin = null;
            List<CustomerDTO> listcuss = null;  
            
            List<String> listStatus = new ArrayList<String>();
            listStatus.add("active");
            listStatus.add("inactive");
            
            if (session != null){       
                    currentAdmin = (AdminDTO) session.getAttribute("adminsession");
            }
            
            if (currentAdmin == null){        
                    response.sendRedirect(request.getContextPath() + "/loginadmin");
                    return;
            }
            
            BookDAO bookDAO = new BookDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            SupplierDAO SupplierDAO = new SupplierDAO();
            
            if(action.equals("customerlist")){
                
                log(keyword);
                
                listcuss = customerDAO.listcuss(keyword);
                
                request.setAttribute("listbook", listcuss);
                RequestDispatcher rd = request.getRequestDispatcher("ListCustomer.jsp");   
                rd.forward(request, response);
            }
            
            if(action.equals("editcustomer")){
                String id = request.getParameter("customer_id");
                
                CustomerDTO customerDTO = null;
                
                if(id != null){
                    customerDTO = customerDAO.detailCustomer(id);   
                }

                request.setAttribute("cussedit", customerDTO);
                request.setAttribute("liststatus", listStatus);
                request.setAttribute("action", "updatecustomer");
                RequestDispatcher rd = request.getRequestDispatcher("editCustomerForAdmin.jsp");
                rd.forward(request, response);
        }
        
        
        if(action.equals("updatecustomer")){

            CustomerDTO cusupdate = new CustomerDTO();
            
            String cussid = request.getParameter("customer_id");
            String password = request.getParameter("password").trim();
            
            if(password.equals("")){
                CustomerDTO take = customerDAO.detailCustomer(cussid);
                String pass = take.getPassword();
                
                log(pass);
                
                cusupdate.setPassword(pass);
            }else if (!password.equals("")){
                cusupdate.setPassword(request.getParameter("password").trim());
            }
            
            cusupdate.setCustomer_id(Integer.parseInt(request.getParameter("customer_id").trim()));
            cusupdate.setCustomer_name(request.getParameter("customer_name").trim());
            cusupdate.setCustomer_address(request.getParameter("customer_address").trim());
            cusupdate.setPhonenumber(request.getParameter("phonenumber").trim());
            cusupdate.setUsername(request.getParameter("username").trim());
            cusupdate.setGender(request.getParameter("gender").trim());
            cusupdate.setStatuses(request.getParameter("statuses").trim());
            
            if(cussid != null){
                customerDAO.update(cusupdate);
            } 
            
            listcuss = customerDAO.listcuss(keyword);
                
            request.setAttribute("listbook", listcuss);
            
            RequestDispatcher rd = request.getRequestDispatcher("ListCustomer.jsp");
            rd.forward(request, response);   
  
        }
        
        
        if(action.equals("createcustomer")){
                request.setAttribute("action", "insertcustomer");
                request.setAttribute("liststatus", listStatus);
                RequestDispatcher rd = request.getRequestDispatcher("editCustomerForAdmin.jsp");
                rd.forward(request, response);
            }
            
        if (action.equals("insertcustomer")){
                 
                boolean check = true;
                int increase = 1;
                int pull = 0;
                int kid = 0;
                
                CustomerDTO cuscurr = new CustomerDTO();
                
                if(request.getParameter("statuses") == null){
                    cuscurr.setStatuses("active");
                }else{
                    cuscurr.setStatuses(request.getParameter("statuses").trim());
                }

                cuscurr.setCustomer_name(request.getParameter("customer_name"));
                cuscurr.setCustomer_address(request.getParameter("customer_address"));
                cuscurr.setPhonenumber(request.getParameter("phonenumber"));
                cuscurr.setUsername(request.getParameter("username"));
                cuscurr.setPassword(request.getParameter("password"));
                cuscurr.setGender(request.getParameter("gender"));
               
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
                    listcuss = customerDAO.listcuss(keyword);
                
                    request.setAttribute("listbook", listcuss);
            
                    RequestDispatcher rd = request.getRequestDispatcher("ListCustomer.jsp");
                    rd.forward(request, response); 
            }
            
        
            
            if (action.equals("deletecustomer")){
                
                String id = request.getParameter("customer_id");     
                
                if (id != null){
                    
                    List<InvoiceDTO> listinvoicecus = invoiceDAO.getAllInvoiceByIdCus(Integer.parseInt(id));

                    for (int i = 0; i < listinvoicecus.size(); i++) {
                        invoiceDAO.deleteInvoiceById(listinvoicecus.get(i).getInvoice_id());
                    }

                    listcuss = customerDAO.listcuss(keyword);
                    
                     customerDAO.deletecustomer(id);
                }
                

                listcuss = customerDAO.listcuss(keyword);
                
                request.setAttribute("listbook", listcuss);
            
                RequestDispatcher rd = request.getRequestDispatcher("ListCustomer.jsp");
                rd.forward(request, response); 
                
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