/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.controller;

import LitJunction.addtocart.Cart;
import LitJunction.addtocart.Item;
import LitJunction.book.BookDAO;
import LitJunction.book.BookDTO;
import LitJunction.customer.CustomerDAO;
import LitJunction.customer.CustomerDTO;
import LitJunction.invoice.InvoiceDAO;
import LitJunction.invoice.InvoiceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "CheckbuyController", urlPatterns = {"/checkbuy"})
public class CheckbuyController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckbuyController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckbuyController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession(true);
        Cart cart = null;
        BookDTO book = null;
        BookDAO bookDAO = new BookDAO();
        Object o = session.getAttribute("cart");
        
        // neu co roi
        if (o != null) {
            cart = (Cart)o;
        } else {
            cart = new Cart();
        }
        String checkform = request.getParameter("checkbuy");
        
        
        CustomerDTO acc = null;
        Object a = session.getAttribute("customersession");
        
        if (cart.getTotalMoney() == 0 || checkform.equals("no")) {
            response.sendRedirect("./customer");
        }
        
        
        else if(a != null) {
            acc = (CustomerDTO) a;
            String address = request.getParameter("customer_address");
            String sdt = request.getParameter("phonenumber");
            InvoiceDAO iDAO = new InvoiceDAO();
            CustomerDAO cusDAO = new CustomerDAO();
            if(acc.getCustomer_address().equals("") && acc.getPhonenumber().equals(""))
                cusDAO.updateAddressAndPhone(acc, address, sdt);
            
            
            List<Integer> buyquantity = new ArrayList<>();
            List<Integer> bookid = new ArrayList<>();
            
            for (int i = 0; i < cart.getItems().size(); i++) {
                buyquantity.add(cart.getItems().get(i).getQuantity());
                bookid.add(cart.getItems().get(i).getBook().getBook_id());
            }
            
            for (int i = 0; i < bookid.size(); i++) {
                book = bookDAO.detail(Integer.toString(bookid.get(i)));
                book.setBook_image(book.getBook_image().substring(7));
                book.setBookstore_quantity(book.getBookstore_quantity() - buyquantity.get(i));              
                bookDAO.edit(book);  
            }
            
            
            // luu invoice xuong db
            iDAO.addInvoice(acc, cart);
            int newInvoice = iDAO.getInvoiceNewCreate();
            session.removeAttribute("cart");
            session.setAttribute("size", 0);
            
            response.sendRedirect("./paysuccess.jsp");
        } else {
            response.sendRedirect("login_customer.jsp");
        }
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
