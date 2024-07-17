/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.admin;

import LitJunction.customer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import LitJunction.utils.DBUtils;


public class AdminDAO{
    
    public AdminDTO login(String username, String password){
          
        String sql = " SELECT admin_name, admin_id , username_admin , password_admin FROM Admin ";
               sql += " WHERE username_admin = ?  AND password_admin = ? ";
        try {
                
                Connection con = DBUtils.getConnection();                     
                PreparedStatement stmt = con.prepareStatement(sql);
                
                stmt.setString(1, username);
                stmt.setString(2, password);
                
                ResultSet rs = stmt.executeQuery();
                
                    if (rs.next()){
                        AdminDTO admin = new AdminDTO();  
                        
                        admin.setAdmin_name(rs.getString("admin_name"));
                        admin.setAdmin_id(rs.getInt("admin_id"));
                        admin.setUsername_admin(rs.getString("username_admin"));
                        admin.setPassword_admin(rs.getString("password_admin"));   
                        
                        return admin;
                    }

            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
            
        return null;
    }
 
}
