/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.controller;

import LitJunction.admin.AdminDTO;
import LitJunction.invoice.InvoiceDAO;
import LitJunction.invoice.InvoiceDTO;
import LitJunction.invoicedetail.InvoicedetailDAO;
import LitJunction.invoicedetail.InvoicedetailDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
public class StatisticController extends HttpServlet {

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
            String invoice_id_raw = request.getParameter("invoice_id");
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
            
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            float t = 0.0f;
            float tOneInvoice = 0.0f;
            List<InvoiceDTO> listinvoice = invoiceDAO.getAllInvoice();
            for (int i = 0; i < listinvoice.size(); i++) {
                t += (float) listinvoice.get(i).getTotal_amount();
            }
            
            
            if (action != null && action.equals("invoice")){
                
                request.setAttribute("listinvoice", listinvoice);
                request.setAttribute("total", t);
                RequestDispatcher rd = request.getRequestDispatcher("statisticinvoice.jsp");  // trang book cua customer
                rd.forward(request, response);
            }
            
            else if (action != null && action.equals("InvoiceDetail")) {
                
                Integer invoice_id = null;
                try {
                    invoice_id = Integer.parseInt(invoice_id_raw);
                } catch (Exception ex) {
                    System.out.println("Error invoice_id parseInt!" + ex.getMessage());
                }
                for (int i = 0; i < listinvoice.size(); i++) {
                if(listinvoice.get(i).getInvoice_id() == invoice_id)
                    tOneInvoice = (float) listinvoice.get(i).getTotal_amount();
                }
                
                InvoicedetailDAO invoiceDetailDAO = new InvoicedetailDAO();
                List<InvoicedetailDTO> listInvoiceDetail = invoiceDetailDAO.getInvoiceDetailById(invoice_id);
                request.setAttribute("tOneInvoice", tOneInvoice);
                request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                RequestDispatcher rd = request.getRequestDispatcher("invoicedetail.jsp"); 
                rd.forward(request, response);
            }
            
            else if (action != null && action.equals("search")){
            String day = request.getParameter("datename");
            float tsearch = 0.0f;
            
            List<InvoiceDTO> listC= (List<InvoiceDTO>) invoiceDAO.listSearchInvoiceCus(day);
                for (int i = 0; i < listC.size(); i++) {
                    tsearch += (float) listC.get(i).getTotal_amount();
                }
            
            request.setAttribute("listinvoice", listC);
            request.setAttribute("total", tsearch);
            RequestDispatcher rd = request.getRequestDispatcher("statisticinvoice.jsp");
            rd.forward(request, response);
        }

            else if (action != null && action.equals("delete")) {
                Integer invoice_id = null;
                try {
                    invoice_id = Integer.parseInt(invoice_id_raw);
                } catch (Exception ex) {
                    System.out.println("Error invoice_id parseInt!" + ex.getMessage());
                }
                
                if (invoice_id != null){
                     invoiceDAO.deleteInvoiceById(invoice_id);
                }
                
                List<InvoiceDTO> listAfterDelete = invoiceDAO.getAllInvoice();
                
                float tADelete = 0.0f;
                for (int i = 0; i < listAfterDelete.size(); i++) {
                    tADelete += (float) listAfterDelete.get(i).getTotal_amount();
                }
                
                request.setAttribute("listinvoice", listAfterDelete);
                request.setAttribute("total", tADelete);
                RequestDispatcher rd = request.getRequestDispatcher("statisticinvoice.jsp");
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
