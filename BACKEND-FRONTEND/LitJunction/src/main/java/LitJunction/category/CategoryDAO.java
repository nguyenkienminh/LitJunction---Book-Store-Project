/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.category;

import LitJunction.book.*;
import LitJunction.admin.*;
import LitJunction.customer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import LitJunction.utils.DBUtils;
import java.util.ArrayList;
import java.util.List;


public class CategoryDAO{
    
    public List<CategoryDTO> listCate(String category){
        
        List<CategoryDTO> catelist ;
        catelist = new ArrayList<CategoryDTO>();
         
        int catecheck = 0;
        
        String sql  = " SELECT category_id , category_name FROM Category "; 
                
        if(category != null && !category.isEmpty() ){
            catecheck = Integer.parseInt(category);
            String alt = " WHERE category_id != ? ";
            sql += alt;
        }         
                
        try {
                Connection con = DBUtils.getConnection(); 
                
                PreparedStatement stmt = con.prepareStatement(sql);
                
                if(category != null && !category.isEmpty() ){
                    stmt.setInt(1, catecheck);
                }
                
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()){
                    catelist.add( new CategoryDTO(
                                rs.getInt("category_id"),
                                rs.getString("category_name")
                        ));          
                                  
                    }
                    return catelist;
                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
            return null;
        
    }
    
    public CategoryDTO detail(String category){
            
            
        
            String sql  = " SELECT category_id , category_name FROM Category "
                        + " WHERE category_id = ? ";
                                
        try {
                Connection con = DBUtils.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql);
                
                int cate = Integer.parseInt(category);
                
                stmt.setInt(1, cate);
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()){
                        return new CategoryDTO(
                                rs.getInt("category_id"),
                                rs.getString("category_name")
                        );               
                }
                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
            return null;
        
    }
    
    public boolean edit(CategoryDTO cate){
        String sql = " Update Category ";               
                sql+=  " SET category_name = ?  ";
                sql+= " WHERE category_id = ? "; 
                
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
              
            ps.setString(1, cate.getCategory_name());
            ps.setInt(2, cate.getCategory_id());
            
            ps.executeUpdate();
            
            return true;
           
	}
        catch (SQLException ex) {
            System.out.println("Insert edit error!" + ex.getMessage());
        }
        return false;
    }
    
    public Long insert(CategoryDTO cate){
               
        String sql = "INSERT INTO Category( category_id , category_name ) "               
                + " VALUES ( ? , ? ) ";    
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setInt(1,cate.getCategory_id());
            ps.setString(2, cate.getCategory_name());
            
            
            ps.executeUpdate();
           
	}
        catch (SQLException ex) {
            System.out.println("Insert category error!" + ex.getMessage());
        }
        return null;
    }
        
    public boolean delete(String cate){
        String sql = " DELETE FROM Category ";               
                sql+= " WHERE category_id = ? "; 
                
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            int cate_D = Integer.parseInt(cate);
            
            ps.setInt(1, cate_D);
            
            ps.executeUpdate();
            
            return true;
           
	}
        catch (SQLException ex) {
            System.out.println("Delete category error!" + ex.getMessage());
        }
        return false;
    }
    
}
