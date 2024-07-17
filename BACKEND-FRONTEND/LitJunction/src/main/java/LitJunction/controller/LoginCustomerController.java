/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.controller;

import LitJunction.addtocart.Cart;
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
import java.util.List;
import javax.servlet.http.HttpSession;



public class LoginCustomerController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        
            String action = request.getParameter("action");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String actionD = (String) request.getAttribute("actionD");

            
            
            if (action != null && action.equals("logout") || actionD != null && actionD.equals("logout")){
                
                HttpSession session = request.getSession(false);
                if (session != null){
                    session.invalidate();  
                    response.sendRedirect("./customer");
                }
                
            }else if(action != null && action.equals("login")){
                
                if (username == null && password == null ){
                    
                    RequestDispatcher rd = request.getRequestDispatcher("/login_customer.jsp");
                    rd.forward(request, response);
                }else{

                    CustomerDAO customerDAO = new CustomerDAO();
                    CustomerDTO customerDTO = customerDAO.login(username, password);     
                    
                    if (customerDTO != null){                        
                        HttpSession session = request.getSession(true);
                        session.setAttribute("customersession", customerDTO);
                        session.setAttribute("name", customerDTO.getCustomer_name());
                        request.getRequestDispatcher("./muahang.jsp").forward(request, response); //nhap thong tin mua hang

                    }else{      
                        request.setAttribute("error", "Customer has a wrong username or password ");            
                        RequestDispatcher rd = request.getRequestDispatcher("login_customer.jsp");
                        rd.forward(request, response);
                    }
                }
                
                
                
            } 

            else {
                
                if (username == null && password == null ){

                    RequestDispatcher rd = request.getRequestDispatcher("/login_customer.jsp");
                    rd.forward(request, response);
                }else{

                    CustomerDAO customerDAO = new CustomerDAO();
                    CustomerDTO customerDTO = customerDAO.login(username, password);            

                    if (customerDTO != null){                        
                        HttpSession session = request.getSession(true);
                        session.setAttribute("customersession", customerDTO);
                        session.setAttribute("name", customerDTO.getCustomer_name());
                        request.getRequestDispatcher("./customer").forward(request, response);

                    }else{      
                        request.setAttribute("error", "Customer has a wrong username or password ");            
                        RequestDispatcher rd = request.getRequestDispatcher("login_customer.jsp");
                        rd.forward(request, response);
                    }              
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