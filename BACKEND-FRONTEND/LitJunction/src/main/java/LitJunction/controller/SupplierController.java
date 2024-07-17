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
import LitJunction.supplier.SupplierDAO;
import LitJunction.supplier.SupplierDTO;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.servlet.http.HttpSession;



public class SupplierController extends HttpServlet {

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
            // Check security
            
            
            HttpSession session = request.getSession(false);
            AdminDTO currentAdmin = null;
                    
            if (session != null){       
                    currentAdmin = (AdminDTO) session.getAttribute("adminsession");
            }
            
            if (currentAdmin == null){        
                    response.sendRedirect(request.getContextPath() + "/loginadmin");
                    return;
            }
            
            SupplierDAO SupplierDAO = new SupplierDAO();
            BookDAO bookDAO = new BookDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            
            if ( action == null|| action.equals("listsupplier")){
                
                List<SupplierDTO> listSupplier = (List<SupplierDTO>) SupplierDAO.listSupplier(keyword);
                
                request.setAttribute("supplierlist", listSupplier);
                RequestDispatcher rd = request.getRequestDispatcher("ListSupplier.jsp"); 
                rd.forward(request, response);
            }
            
            if(action.equals("editsupplier")){ 
                String idS = request.getParameter("supplier_id");
                SupplierDTO supplierDTO = null;

                if(idS != null){
                    supplierDTO = SupplierDAO.detail(idS);
                }
                
                request.setAttribute("objectSupplier", supplierDTO);

                request.setAttribute("action", "updatesupplier");
                RequestDispatcher rd = request.getRequestDispatcher("editSupplier.jsp");
                rd.forward(request, response);
                
            }
            
            if (action.equals("updatesupplier")){
                 
                SupplierDTO supplierupdate = new SupplierDTO();
                
                String id = request.getParameter("supplier_id");
                
                supplierupdate.setSupplier_id(Integer.parseInt(request.getParameter("supplier_id"))); 
                supplierupdate.setSupplier_name(request.getParameter("supplier_name"));
                supplierupdate.setSupplier_address(request.getParameter("supplier_address"));
             
                if(id != null){
                    SupplierDAO.edit(supplierupdate);
                }
                List<SupplierDTO> listSupplier = (List<SupplierDTO>) SupplierDAO.listSupplier(keyword);
                request.setAttribute("supplierlist", listSupplier);  
                RequestDispatcher rd = request.getRequestDispatcher("ListSupplier.jsp");
                rd.forward(request, response);
            }
            
            if(action.equals("createsupplier")){
                request.setAttribute("action", "insertsupplier" );
                RequestDispatcher rd = request.getRequestDispatcher("editSupplier.jsp");
                rd.forward(request, response);
            }
            
            if (action.equals("insertsupplier")){
                 
                SupplierDTO supplierinsert = new SupplierDTO();
                
                supplierinsert.setSupplier_name(request.getParameter("supplier_name")); 
                supplierinsert.setSupplier_address(request.getParameter("supplier_address"));
                supplierinsert.setSupplier_id(SupplierDAO.getIDsupplier());
                
                SupplierDAO.insert(supplierinsert, currentAdmin);
                
                List<SupplierDTO> listSupplier = (List<SupplierDTO>) SupplierDAO.listSupplier(keyword);
                request.setAttribute("supplierlist", listSupplier); 
                RequestDispatcher rd = request.getRequestDispatcher("ListSupplier.jsp");
                rd.forward(request, response);
            }
            
            //CHƯA FIX ĐC
            if (action.equals("deletesupplier")){
                
                String idS = request.getParameter("supplier_id");     
                
                if (idS != null){
                    
                    List<BookDTO> listdelete = (List<BookDTO>) SupplierDAO.getBookByIdSupplier(Integer.parseInt(idS));
                    log("" + listdelete.size());
                    log("" + idS);
                    for (int i = 0; i < listdelete.size(); i++) {
                        bookDAO.delete(Integer.toString(listdelete.get(i).getBook_id()));
                    }
                    
                    SupplierDAO.delete(idS);
                }

                
                List<SupplierDTO> listSupplier = (List<SupplierDTO>) SupplierDAO.listSupplier(keyword);
                request.setAttribute("supplierlist", listSupplier); 
                RequestDispatcher rd = request.getRequestDispatcher("ListSupplier.jsp");
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