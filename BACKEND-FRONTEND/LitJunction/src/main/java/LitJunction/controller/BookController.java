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
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.servlet.http.HttpSession;
import LitJunction.supplier.SupplierDAO;
import LitJunction.supplier.SupplierDTO;


public class BookController extends HttpServlet {

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
            
            
            
            HttpSession session = request.getSession(false);
            AdminDTO currentAdmin = null;
            List<CategoryDTO> listcate = null;  
            
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
            
            
            
            List<BookDTO> listb = bookDAO.getAllBook();
            // phan trang cho book
            int page, numperpage = 8;
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
            
            
            
            if (action == null || action.equals("list")){
                
                if(keyword != null && !keyword.trim().isEmpty()){
                    listPerPage = (List<BookDTO>) bookDAO.list(keyword, cateC);
                }
                
                if(cateC != null && !cateC.trim().isEmpty()){
                    listPerPage = (List<BookDTO>) bookDAO.list(keyword, cateC);
                }
                
                String category = "";     
                listcate = (List<CategoryDTO>) categoryDAO.listCate(category.trim());
                
                request.setAttribute("cateList", listcate);
                request.setAttribute("listbook", listPerPage);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                RequestDispatcher rd = request.getRequestDispatcher("viewbookadmin.jsp");  // trang book cua admin quan lý sach 
                rd.forward(request, response);
            }
            
            if (action != null && action.equals("outofstock")){
                
                listPerPage = (List<BookDTO>) bookDAO.getAllBookOutOfStock();
                
                String category = "";     
                listcate = (List<CategoryDTO>) categoryDAO.listCate(category.trim());
                
                request.setAttribute("cateList", listcate);
                request.setAttribute("listbook", listPerPage);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                RequestDispatcher rd = request.getRequestDispatcher("viewbookadmin.jsp");  // trang book cua admin quan lý sach 
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
                    RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");  // trang book detail
                    rd.forward(request, response);
                }
                
            }
            
            if(action.equals("edit")){ // lấy thông tin để update.
                String id = request.getParameter("id");
                
                CategoryDTO catedto = null;
                BookDTO bookedit = null;
                
                SupplierDTO suppdto = null;
                
                if(id != null){
                    bookedit = bookDAO.detail(id);
                    bookedit.setBook_image(bookedit.getBook_image().substring(7));
                    String category = Integer.toString(bookedit.getCategory_id());
                    String idS = Integer.toString(bookedit.getSupplier_id());
                    
                    catedto = categoryDAO.detail(category);
                    suppdto = SupplierDAO.detail(idS);
                    listcate = (List<CategoryDTO>) categoryDAO.listCate(category);
                }
                request.setAttribute("suppObject", suppdto);
                request.setAttribute("cateList", listcate);
                request.setAttribute("cateObject", catedto);
                request.setAttribute("bookafter", bookedit);
                request.setAttribute("action", "update");
                RequestDispatcher rd = request.getRequestDispatcher("editbook.jsp");
                rd.forward(request, response);
                
            }
            
            if (action.equals("update")){
                 
                boolean check = true;
                String url = null;
                
                BookDTO bookupdate = new BookDTO();
                CategoryDTO catedto = new CategoryDTO();
                SupplierDTO suppdto = new SupplierDTO();
                
                String id = request.getParameter("book_Id").trim();
                String category = request.getParameter("Category_id").trim();
                
                String name  = request.getParameter("supplier_name").trim();
                String address = request.getParameter("supplier_address").trim();
                
                
                if(request.getParameter("Price").trim().matches("-?\\d+(\\.\\d+)?") && request.getParameter("book_Id").trim().matches("-?\\d+(\\.\\d+)?") && 
                    request.getParameter("Quantity").trim().matches("-?\\d+(\\.\\d+)?")){
                    bookupdate.setPrice(Float.parseFloat(request.getParameter("Price").trim())); 
                    bookupdate.setBook_id(Integer.parseInt(request.getParameter("book_Id").trim())); 
                    bookupdate.setBookstore_quantity(Integer.parseInt(request.getParameter("Quantity").trim()));
                    check = true;
                } else{
                    check = false;
                    url = "editbook.jsp";
                }
                
                bookupdate.setTitle(request.getParameter("Title").trim());
                bookupdate.setBook_image(request.getParameter("Book_image").trim());
                bookupdate.setAuthor(request.getParameter("Author").trim());
                bookupdate.setDescribe(request.getParameter("Describe").trim()); 
                bookupdate.setCategory_id(Integer.parseInt(request.getParameter("Category_id").trim()));
                
                suppdto.setSupplier_name(request.getParameter("supplier_name").trim());
                suppdto.setSupplier_address(request.getParameter("supplier_address").trim());
                
                if(id != null && check == true){
                    
                    int supplier_i = SupplierDAO.load(name, address);
                    
                    if(supplier_i == -1){
                        suppdto.setSupplier_id(SupplierDAO.getIDsupplier());
                        SupplierDAO.insert(suppdto, currentAdmin);
                        suppdto.setSupplier_id(SupplierDAO.load(name, address));
                        bookupdate.setSupplier_id(suppdto.getSupplier_id());
                        
                    }else if (supplier_i != -1){
                        suppdto.setSupplier_id(supplier_i);
                        bookupdate.setSupplier_id(suppdto.getSupplier_id());
                    }
                    suppdto = SupplierDAO.detail(Integer.toString(bookupdate.getSupplier_id()));
                    bookDAO.edit(bookupdate);
                    catedto = categoryDAO.detail(category);
                    request.setAttribute("detailsupp", suppdto);
                    request.setAttribute("detail", bookDAO.detail(id));
                    request.setAttribute("detailCate", catedto);  
                    url = "detail.jsp";
                }
                
                if(check == false){
                    BookDTO bookedit = null;
                    if(id != null){
                    bookedit = bookDAO.detail(id);
                    bookedit.setBook_image(bookedit.getBook_image().substring(7));
                    //bookedit = bookDAO.detail(id);
                    String categoryT = Integer.toString(bookedit.getCategory_id());
                    String idS = Integer.toString(bookedit.getSupplier_id());
                    
                    catedto = categoryDAO.detail(categoryT);
                    suppdto = SupplierDAO.detail(idS);
                    listcate = (List<CategoryDTO>) categoryDAO.listCate(category);
                }
                request.setAttribute("suppObject", suppdto);
                request.setAttribute("cateList", listcate);
                request.setAttribute("cateObject", catedto);
                request.setAttribute("bookafter", bookedit);
                request.setAttribute("action", "update");
                }
                
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            
            if(action.equals("create")){
                String category = null;
                 listcate = (List<CategoryDTO>) categoryDAO.listCate(category);
                request.setAttribute("action", "insert" );
                request.setAttribute("cateList", listcate);
                RequestDispatcher rd = request.getRequestDispatcher("editbook.jsp");
                rd.forward(request, response);
            }
            
            if (action.equals("insert")){
                 
                String url = null;
                boolean check = true;
                
                BookDTO bookinsert = new BookDTO();
                CategoryDTO catedto = new CategoryDTO();
                SupplierDTO suppdto = new SupplierDTO();
                
                String id = request.getParameter("book_Id");
                String category = request.getParameter("Category_id");
                                
                String name  = request.getParameter("supplier_name").trim();
                String address = request.getParameter("supplier_address").trim();
                
                if(request.getParameter("Price").trim().matches("-?\\d+(\\.\\d+)?") && request.getParameter("book_Id").trim().matches("-?\\d+(\\.\\d+)?") && 
                    request.getParameter("Quantity").trim().matches("-?\\d+(\\.\\d+)?")){
                    bookinsert.setPrice(Float.parseFloat(request.getParameter("Price").trim()));
                    bookinsert.setBook_id(Integer.parseInt(request.getParameter("book_Id")));
                    bookinsert.setBookstore_quantity(Integer.parseInt(request.getParameter("Quantity")));
                    check = true;
                } else{
                    check = false;
                    url = "editbook.jsp";
                }
                
                bookinsert.setTitle(request.getParameter("Title"));
                bookinsert.setBook_image(request.getParameter("Book_image"));
                bookinsert.setAuthor(request.getParameter("Author"));
                bookinsert.setDescribe(request.getParameter("Describe").trim()); 
                bookinsert.setCategory_id(Integer.parseInt(request.getParameter("Category_id")));
                             
                suppdto.setSupplier_name(name);
                suppdto.setSupplier_address(address);
                
                if(check == true){
                    
                    int supplier_i = SupplierDAO.load(name, address);
                    
                    if(supplier_i == -1){
                        suppdto.setSupplier_id(SupplierDAO.getIDsupplier());
                        SupplierDAO.insert(suppdto, currentAdmin);
                        suppdto.setSupplier_id(SupplierDAO.load(name, address));
                        bookinsert.setSupplier_id(suppdto.getSupplier_id());
                        
                    }else if (supplier_i != -1){
                        suppdto.setSupplier_id(supplier_i);
                        bookinsert.setSupplier_id(suppdto.getSupplier_id());
                    }
                    
                    suppdto = SupplierDAO.detail(Integer.toString(bookinsert.getSupplier_id()));
                    bookDAO.insert(bookinsert , currentAdmin);
                    catedto = categoryDAO.detail(category);
                    request.setAttribute("detailsupp", suppdto);
                    request.setAttribute("detail", bookDAO.detail(id));
                    request.setAttribute("detailCate", catedto);      
                    url = "detail.jsp"; 
                }
                
                if(check == false){
                    String categoryT = null;
                    listcate = (List<CategoryDTO>) categoryDAO.listCate(categoryT);
                    request.setAttribute("action", "insert" );
                    request.setAttribute("cateList", listcate);
                }
                
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                
            }
            
            if (action.equals("delete")){
                
                String id = request.getParameter("id");     
                
                if (id != null){
                     bookDAO.delete(id);
                }
                
                //listPerPage = (List<BookDTO>) bookDAO.list(keyword, cateC);
                String category = "";     
                listcate = (List<CategoryDTO>) categoryDAO.listCate(category.trim());
                
                request.setAttribute("cateList", listcate);
                request.setAttribute("listbook", listPerPage);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                
                RequestDispatcher rd = request.getRequestDispatcher("viewbookadmin.jsp");
                rd.forward(request, response);
                
            }
            
            if(action.equals("createcategory") ||action.equals("listcategory") || action.equals("insertcategory") || action.equals("updatecategory")){
                
                if(action.equals("insertcategory") || action.equals("updatecategory")){
                    
                    String[] cateidlist = request.getParameterValues("category_id");
                    String[] catenamelist = request.getParameterValues("category_name");
                    
                    CategoryDTO cateafter = new CategoryDTO();
                    int count = 0;
                    
                    for (int i = 0; i < cateidlist.length; i++) {
                        cateafter.setCategory_id(Integer.parseInt(cateidlist[i]));
                        cateafter.setCategory_name(catenamelist[count]);
                        
                        if(action.equals("updatecategory")){
                                categoryDAO.edit(cateafter);     
                                count++;
                        }else if (action.equals("insertcategory")){
                            categoryDAO.insert(cateafter);
                            count++;
                        }
                    }  
                }
                
                String category = null;
                listcate = (List<CategoryDTO>) categoryDAO.listCate(category);
                
                request.setAttribute("cateList", listcate);
                
                if(action.equals("createcategory")){
                    request.setAttribute("action", "insertcategory" );
                    RequestDispatcher rd = request.getRequestDispatcher("editCategory.jsp");
                    rd.forward(request, response);
                        
                } else if (action.equals("listcategory") || action.equals("insertcategory") || action.equals("updatecategory")){
                    RequestDispatcher rd = request.getRequestDispatcher("ListCategory.jsp");
                    rd.forward(request, response);
                    
                }
            }
            
            if(action.equals("editcategory")){
                
                String idcate = request.getParameter("category_id");     
                CategoryDTO catedto = new CategoryDTO();
                
                if (idcate != null){
                    catedto = categoryDAO.detail(idcate);
                }
                
                request.setAttribute("detailCate", catedto);
                request.setAttribute("action", "updatecategory" );
                RequestDispatcher rd = request.getRequestDispatcher("editCategory.jsp");
                rd.forward(request, response);   
            }
            
            if(action.equals("deletecategory")){
                
                String idcate = request.getParameter("category_id");     
                boolean check = true;
                        
                if (idcate != null){
                    
                    List<BookDTO> listbookdelete = (List<BookDTO>) bookDAO.list(keyword, idcate);
                    
                    for (int i = 0; i < listbookdelete.size(); i++) {
                        bookDAO.delete(Integer.toString(listbookdelete.get(i).getBook_id()));
                    }
                    
                    check = categoryDAO.delete(idcate);
                }
                
                String category = null;
                 listcate = (List<CategoryDTO>) categoryDAO.listCate(category);
                
                
                if(check == true){
                    request.setAttribute("status", " Delete Successfuly !");
                } else if (check == false){
                    request.setAttribute("status", " Delete Fail !");
                }
                request.setAttribute("cateList", listcate);
                RequestDispatcher rd = request.getRequestDispatcher("ListCategory.jsp");
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