/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.supplier;

import LitJunction.category.*;
import LitJunction.book.*;
import LitJunction.admin.*;
import LitJunction.customer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import LitJunction.utils.DBUtils;
import static java.lang.Math.log;
import java.util.ArrayList;
import java.util.List;


public class SupplierDAO{
    
    public List<SupplierDTO> listSupplier(String keyword){
        
        List<SupplierDTO> supplierlist ;
        supplierlist = new ArrayList<SupplierDTO>();
         
        String sql  = " SELECT supplier_id , supplier_name , supplier_address , admin_id FROM Supplier "; 
                
        if(keyword != null && !keyword.isEmpty()){
            String alt = " WHERE supplier_name like ? OR supplier_address like ? ";
            sql += alt;
        }         
                
        try {
                Connection con = DBUtils.getConnection(); 
                
                PreparedStatement stmt = con.prepareStatement(sql);
                
                if (keyword != null && !keyword.isEmpty()){
                    stmt.setString(1, "%" + keyword + "%");
                    stmt.setString(2, "%" + keyword + "%");
                }
                
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()){
                    supplierlist.add(new SupplierDTO(
                                rs.getInt("supplier_id"),
                                rs.getString("supplier_name"),
                                rs.getString("supplier_address"),
                                rs.getInt("admin_id")
                        ));          
                                  
                    }
                    return supplierlist;
                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
            return null;
        
    }
    
    public int load(String supplier_name, String supplier_address ){
            
        String sql  = " SELECT supplier_id FROM Supplier "
                    + " WHERE supplier_name like ? AND supplier_address like ? ";
                                
        try {
                Connection con = DBUtils.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql);
                
                
                stmt.setString(1, supplier_name);
                stmt.setString(2, supplier_address);
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    int count = rs.getInt("supplier_id");
                    return count;
                }
                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
            return -1;
        
    }
    
    public SupplierDTO detail(String supplier){
            
        String sql  = " SELECT supplier_id , supplier_name , supplier_address , admin_id FROM Supplier "
                        + " WHERE supplier_id = ? ";
                                
        try {
                Connection con = DBUtils.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql);
                
                
                int supp = Integer.parseInt(supplier);
                
                stmt.setInt(1, supp);
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()){
                        return new SupplierDTO(
                                rs.getInt("supplier_id"),
                                rs.getString("supplier_name"),
                                rs.getString("supplier_address"),
                                rs.getInt("admin_id")
                        );               
                }
                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
            return null;
        
    }
    
    public boolean edit(SupplierDTO supp){
        String sql = " Update Supplier ";               
                sql+=  " SET supplier_name = ? , supplier_address = ?  ";
                sql+= " WHERE supplier_id = ? "; 
                
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
              
            ps.setString(1, supp.getSupplier_name());
            ps.setString(2, supp.getSupplier_address());
            ps.setInt(3, supp.getSupplier_id());
            
            ps.executeUpdate();
            
            return true;
           
	}
        catch (SQLException ex) {
            System.out.println("Insert Supplier error!" + ex.getMessage());
        }
        return false;
    }
    
    public Long insert(SupplierDTO supp , AdminDTO admin){
               
        String sql = "INSERT INTO Supplier( supplier_id , supplier_name , supplier_address , admin_id ) "               
                + " VALUES ( ? , ? , ? , ? ) ";    
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setInt(1,supp.getSupplier_id());
            ps.setString(2, supp.getSupplier_name());
            ps.setString(3, supp.getSupplier_address());
            ps.setInt(4, admin.getAdmin_id());
            
            ps.executeUpdate();
           
	}
        catch (SQLException ex) {
            System.out.println("Insert Student error!" + ex.getMessage());
        }
        return null;
    }
    
    public int getIDsupplier(){
        
        String sql = " SELECT COUNT(supplier_id) + 1  FROM Supplier ";
        
        try {
            Connection conn = DBUtils.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public boolean delete(String idS){
        String sql = " DELETE FROM Supplier ";               
                sql+= " WHERE supplier_id = ? "; 
                
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            int supp_id = Integer.parseInt(idS);
            
            ps.setInt(1, supp_id);
            
            ps.executeUpdate();
            
            return true;
           
	}
        catch (SQLException ex) {
            System.out.println("Delete supplier error!" + ex.getMessage());
        }
        return false;
    }
    
    public List<BookDTO> getBookByIdSupplier(int supp_id) {
        String sql = "SELECT * FROM Book WHERE supplier_id = ? ";
        List<BookDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, supp_id);
            ResultSet rs = ps.executeQuery();
            if(rs != null) {
            while(rs.next()) {
                BookDTO b = new BookDTO(
                                rs.getInt("book_id"), rs.getString("book_image"), 
                                rs.getString("title"), rs.getString("author"), 
                                rs.getFloat("price"), rs.getString("describe"), 
                                rs.getInt("bookstore_quantity"), rs.getInt("admin_id") ,
                                rs.getInt("category_id") , rs.getInt(supp_id));
                list.add(b);
            }}
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error getBookById!" + ex.getMessage());
        }
        return list;
    }
    
}
