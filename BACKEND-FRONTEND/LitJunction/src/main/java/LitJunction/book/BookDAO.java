/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.book;

import LitJunction.admin.*;
import LitJunction.customer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import LitJunction.utils.DBUtils;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;



public class BookDAO{
    
    public List<BookDTO> listCus(String keyword, String cate_id){
        
            List<BookDTO> booklist ;
            booklist = new ArrayList<BookDTO>();
            
                String sql  = " SELECT book_id , book_image , title , author , price , describe , "
                        + " bookstore_quantity , admin_id , category_id , supplier_id FROM Book WHERE bookstore_quantity > 1 ";
                
                if(keyword != null && !keyword.isEmpty()){ 
                    String alt = " AND author like ? OR title like ? ";
                    sql += alt;
                } 
                
                if(cate_id != null && !cate_id.trim().isEmpty()){
                    String cte = " AND category_id = ? ";
                    sql += cte;
                }
                
        try {
                Connection con = DBUtils.getConnection(); 
                
                PreparedStatement stmt = con.prepareStatement(sql);
                
                if (keyword != null && !keyword.isEmpty()){
                    stmt.setString(1, "%" + keyword + "%");
                    stmt.setString(2, "%" + keyword + "%");
                }
                
                if(cate_id != null && !cate_id.isEmpty()){
                    stmt.setInt(1, Integer.parseInt(cate_id));
                }
                
                ResultSet rs = stmt.executeQuery();
                
                if(rs != null){
                    while (rs.next()){
                        booklist.add( new BookDTO(
                                        rs.getInt("book_id"), rs.getString("book_image"), 
                                        rs.getString("title"), rs.getString("author"), 
                                        rs.getFloat("price"), rs.getString("describe"), 
                                        rs.getInt("bookstore_quantity"), rs.getInt("admin_id") ,
                                        rs.getInt("category_id") , rs.getInt("supplier_id")
                                ));
                    }          
                }
                con.close();
                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
            return booklist;
        
    }
    
    public List<BookDTO> list(String keyword, String cate_id){
        
            List<BookDTO> booklist ;
            booklist = new ArrayList<BookDTO>();
            
                String sql  = " SELECT book_id , book_image , title , author , price , describe , "
                        + " bookstore_quantity , admin_id , category_id , supplier_id FROM Book ";
                
                if(keyword != null && !keyword.isEmpty()){ 
                    String alt = " WHERE author like ? OR title like ?  ";
                    sql += alt;
                } 
                
                if(cate_id != null && !cate_id.trim().isEmpty()){
                    String cte = " WHERE category_id = ? ";
                    sql += cte;
                }
                
        try {
                Connection con = DBUtils.getConnection(); 
                
                PreparedStatement stmt = con.prepareStatement(sql);
                
                if (keyword != null && !keyword.isEmpty()){
                    stmt.setString(1, "%" + keyword + "%");
                    stmt.setString(2, "%" + keyword + "%");
                }
                
                if(cate_id != null && !cate_id.isEmpty()){
                    stmt.setInt(1, Integer.parseInt(cate_id));
                }
                
                ResultSet rs = stmt.executeQuery();
                
                if(rs != null){
                    while (rs.next()){
                        booklist.add( new BookDTO(
                                        rs.getInt("book_id"), rs.getString("book_image"), 
                                        rs.getString("title"), rs.getString("author"), 
                                        rs.getFloat("price"), rs.getString("describe"), 
                                        rs.getInt("bookstore_quantity"), rs.getInt("admin_id") ,
                                        rs.getInt("category_id") , rs.getInt("supplier_id")
                                ));
                    }          
                }
                con.close();
                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
            return booklist;
        
    }
    
    
    
    public BookDTO detail(String image_id){
        
            String sql  = " SELECT book_id , book_image , title , author , price , describe , "
                        + " bookstore_quantity , admin_id , category_id , supplier_id FROM Book "
                        + " WHERE book_id = ? ";
                                
        try {
                Connection con = DBUtils.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql);
                
                int im_id = Integer.parseInt(image_id);
                stmt.setInt(1, im_id);
                
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()){
                        return new BookDTO(
                                rs.getInt("book_id"), rs.getString("book_image"), 
                                rs.getString("title"), rs.getString("author"), 
                                rs.getFloat("price"), rs.getString("describe"), 
                                rs.getInt("bookstore_quantity"), rs.getInt("admin_id") ,
                                rs.getInt("category_id") , rs.getInt("supplier_id")
                        );               
                }
                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
            return null;
        
    }
    
    public boolean edit(BookDTO book){
        String sql = "Update Book ";               
                sql+=  " SET book_image = ? , title = ? , author = ? , price = ? , describe = ? , bookstore_quantity = ? , category_id = ? , supplier_id = ? ";
                sql+= " WHERE book_id = ? "; 
                
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
              
            ps.setString(1,"images/" + book.getBook_image());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setFloat(4, book.getPrice());
            ps.setString(5, book.getDescribe());
            ps.setInt(6, book.getBookstore_quantity());
            ps.setInt(7, book.getCategory_id());
            ps.setInt(8, book.getSupplier_id());
            ps.setLong(9, book.getBook_id());
            
            ps.executeUpdate();
            
            return true;
           
	}
        catch (SQLException ex) {
            System.out.println("Insert Student error!" + ex.getMessage());
        }
        return false;
    }
    
    public Long insert(BookDTO book , AdminDTO admin){
            
            
        String sql = "INSERT INTO Book( book_id , book_image , title , author , price , describe ,  bookstore_quantity , category_id , supplier_id , admin_id ) "               
                + " VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";    
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setInt(1,book.getBook_id());
            ps.setString(2, "images/" + book.getBook_image());
            ps.setString(3, book.getTitle());
            ps.setString(4, book.getAuthor());
            ps.setFloat(5, book.getPrice());
            ps.setString(6, book.getDescribe());
            ps.setInt(7, book.getBookstore_quantity());
            ps.setInt(8, book.getCategory_id());
            ps.setInt(9, book.getSupplier_id());
            ps.setInt(10, admin.getAdmin_id());
            
            ps.executeUpdate();
           
	}
        catch (SQLException ex) {
            System.out.println("Insert book error!" + ex.getMessage());
        }
        return null;
    }
        
    public boolean delete(String book){
        String sql = "DELETE FROM Book ";               
                sql+= " WHERE book_id = ? "; 
                
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            int book_N = Integer.parseInt(book);
            
            ps.setInt(1, book_N);
            
            ps.executeUpdate();
            
            return true;
           
	}
        catch (SQLException ex) {
            System.out.println("Delete Book error!" + ex.getMessage());
        }
        return false;
    }
    
    // lay het san pham
    public List<BookDTO> getAllBook() {
        List<BookDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Book";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                BookDTO b = new BookDTO(
                                rs.getInt("book_id"), rs.getString("book_image"), 
                                rs.getString("title"), rs.getString("author"), 
                                rs.getFloat("price"), rs.getString("describe"), 
                                rs.getInt("bookstore_quantity"), rs.getInt("admin_id") ,
                                rs.getInt("category_id") , rs.getInt("supplier_id")
                        );
                list.add(b);
            }
        } catch(Exception ex) {
            System.out.println("Error getAllBook!" + ex.getMessage());
        }
        
        return list;
    }
    
    public List<BookDTO> getAllBookOutOfStock() {
        List<BookDTO> list = new ArrayList<>();
        String sql = " SELECT * FROM Book WHERE bookstore_quantity <= 1 ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs != null){
            while(rs.next()) {
                BookDTO b = new BookDTO(
                                rs.getInt("book_id"), rs.getString("book_image"), 
                                rs.getString("title"), rs.getString("author"), 
                                rs.getFloat("price"), rs.getString("describe"), 
                                rs.getInt("bookstore_quantity"), rs.getInt("admin_id") ,
                                rs.getInt("category_id") , rs.getInt("supplier_id")
                        );
                list.add(b);
            }
            }
        } catch(Exception ex) {
            System.out.println("Error getAllBook!" + ex.getMessage());
        }
        
        return list;
    }
    
    public BookDTO getBookById(int id) {
        String sql = "SELECT * FROM Book WHERE book_id = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new BookDTO(id, rs.getString("book_image"), 
                                rs.getString("title"), rs.getString("author"), 
                                rs.getFloat("price"), rs.getString("describe"), 
                                rs.getInt("bookstore_quantity"), rs.getInt("admin_id") ,
                                rs.getInt("category_id") , rs.getInt("supplier_id"));
            }
            
        } catch (Exception ex) {
            System.out.println("Error getBookById!" + ex.getMessage());
        }
        return null;
    }
    
    // chia book theo trang
    public List<BookDTO> getListByPage(List<BookDTO> list, int start, int end) {
        ArrayList<BookDTO> a = new ArrayList<>();
        for(int i = start; i < end; i++){
            a.add(list.get(i));
        }
        return a;
    }
    
    public List<BookDTO> getAllBookCustomer() {
        List<BookDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE bookstore_quantity > 1 ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                BookDTO b = new BookDTO(
                                rs.getInt("book_id"), rs.getString("book_image"), 
                                rs.getString("title"), rs.getString("author"), 
                                rs.getFloat("price"), rs.getString("describe"), 
                                rs.getInt("bookstore_quantity"), rs.getInt("admin_id") ,
                                rs.getInt("category_id") , rs.getInt("supplier_id")
                        );
                list.add(b);
            }
        } catch(Exception ex) {
            System.out.println("Error getAllBook!" + ex.getMessage());
        }
        
        return list;
    }
    
}
