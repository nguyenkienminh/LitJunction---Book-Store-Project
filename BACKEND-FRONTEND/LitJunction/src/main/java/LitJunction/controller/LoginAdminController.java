/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.controller;

import LitJunction.admin.AdminDAO;
import LitJunction.admin.AdminDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import LitJunction.customer.CustomerDAO;
import LitJunction.customer.CustomerDTO;
import javax.servlet.http.HttpSession;



public class LoginAdminController extends HttpServlet {

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
            String username = request.getParameter("username_A");
            String password = request.getParameter("password_A");
            
//            RequestDispatcher rd1 = request.getRequestDispatcher("menu.html");
//            rd1.include(request, response);
            
            if (action != null && action.equals("logout")){
                HttpSession session = request.getSession(false); 
                
                if (session != null){
                    session.invalidate();
                    request.setAttribute("error_A", "Admin has a logout successfully ");
                    RequestDispatcher rd = request.getRequestDispatcher("/login_admin.jsp");
                    rd.forward(request, response);
                }
                
            }else if (action == null || action.equals("login")){
                
                if (username == null && password == null ){
                    request.setAttribute("error_A", "Admin has a wrong username or password");
                    RequestDispatcher rd = request.getRequestDispatcher("/login_admin.jsp");
                    rd.forward(request, response);
                }else{

                    AdminDAO adminDAO = new AdminDAO();
                    AdminDTO adminDTO = adminDAO.login(username, password);            

                    if (adminDTO != null){                        
                        HttpSession session = request.getSession(true);
                        session.setAttribute("adminsession", adminDTO);                   
                        response.sendRedirect("book");

                    }else{      
                        request.setAttribute("error_A", "Admin has a wrong username or password ");            
                        RequestDispatcher rd = request.getRequestDispatcher("/login_admin.jsp");
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