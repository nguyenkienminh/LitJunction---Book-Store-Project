/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import LitJunction.utils.DBUtils;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO{
    
    public List<CustomerDTO> listcuss(String keyword){
        
        List<CustomerDTO> cusslist ;
            cusslist = new ArrayList<CustomerDTO>();
        
        String sql = " SELECT customer_id , customer_name , customer_address , phonenumber , current_points , "
                        + " username , password , gender , admin_id , statuses FROM Customer ";
        
        if(keyword != null && !keyword.isEmpty()){
            String alt = " WHERE customer_name like ? ";
            sql += alt;
        } 

        try {
                
                Connection con = DBUtils.getConnection();            
                             
                PreparedStatement stmt = con.prepareStatement(sql);
                
                if (keyword != null && !keyword.isEmpty()){
                    stmt.setString(1, "%" + keyword + "%");
                    
                }
                
                ResultSet rs = stmt.executeQuery();
                
                    if(rs != null){
                        while (rs.next()){
                            
                            cusslist.add( new CustomerDTO(rs.getInt("customer_id"), 
                                                          rs.getString("customer_name"), 
                                                          rs.getString("customer_address"), 
                                                          rs.getString("phonenumber"), 
                                                          rs.getInt("current_points"), 
                                                          rs.getString("username"), 
                                                          rs.getString("password"), 
                                                          rs.getString("gender"), 
                                                          rs.getInt("admin_id"),
                                                          rs.getString("statuses")
                                        ));
                                        
                        }          
                    }
                    
                con.close();                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
        return cusslist;
    }
    
    
    public CustomerDTO login(String username, String password){
        
        String sql = " SELECT customer_id , customer_name , customer_address , phonenumber , current_points , "
                        + " username , password , gender , admin_id FROM Customer ";
                sql += " WHERE username = ?  AND password = ? AND statuses = ? ";
        
        CustomerDTO customer;
        customer = new CustomerDTO();
        
        try {

                Connection con = DBUtils.getConnection();            
                             
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, "active");
                
                ResultSet rs = stmt.executeQuery();
                
                    if (rs.next()){
                                               
                        customer.setCustomer_id(rs.getInt("customer_id"));
                        customer.setCustomer_name(rs.getString("customer_name"));
                        customer.setCustomer_address(rs.getString("customer_address"));
                        customer.setPhonenumber(rs.getString("phonenumber"));
                        customer.setCurrent_points(rs.getInt("current_points"));
                        customer.setUsername(rs.getString("username"));
                        customer.setPassword(rs.getString("password"));
                        customer.setAdmin_id(rs.getInt("admin_id"));
                        customer.setGender(rs.getString("gender"));
                        
                        return customer;
                    }                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
        return null;
    }
    
    
    public CustomerDTO detailCustomer (String customer_id){
        
        String sql = " SELECT gender ,customer_id , customer_name , customer_address , phonenumber , current_points , "
                        + " username , password , gender , admin_id , statuses FROM Customer ";
                sql += " WHERE customer_id = ? ";
        
        CustomerDTO customer;
        customer = new CustomerDTO();
        
        try {

                Connection con = DBUtils.getConnection();            
                             
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, customer_id);
                
                ResultSet rs = stmt.executeQuery();
                
                    if (rs.next()){
                                               
                        customer.setCustomer_id(rs.getInt("customer_id"));
                        customer.setCustomer_name(rs.getString("customer_name"));
                        customer.setCustomer_address(rs.getString("customer_address"));
                        customer.setPhonenumber(rs.getString("phonenumber"));
                        customer.setCurrent_points(rs.getInt("current_points"));
                        customer.setUsername(rs.getString("username"));
                        customer.setPassword(rs.getString("password"));
                        customer.setAdmin_id(rs.getInt("admin_id"));
                        customer.setGender(rs.getString("gender"));
                        customer.setStatuses(rs.getString("statuses"));
                        
                        return customer;
                    }                
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            }
        return null;
    }
    
    public int getIDCustomer(int inc){
        
        String sql = " SELECT customer_id  FROM Customer ";
               sql+= " WHERE customer_id = ? "; 
        try {
            Connection conn = DBUtils.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, inc);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt(1);
                return id;
            }
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public boolean checkIDCustomer(int check){
        
        String sql = " SELECT customer_id  FROM Customer ";
               sql += " WHERE customer_id = ? ";
        
        try {
            Connection conn = DBUtils.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, check);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                if(Integer.toString(rs.getInt(1)) != null){
                    return false;
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public Long insert(CustomerDTO cuss){
               
        String sql = "INSERT INTO Customer( customer_id , customer_name , customer_address , phonenumber , current_points ,  "
                + " username , password , gender , admin_id , statuses ) "               
                + " VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";    
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setInt(1,cuss.getCustomer_id());
            ps.setString(2, cuss.getCustomer_name());
            ps.setString(3, cuss.getCustomer_address());
            ps.setString(4, cuss.getPhonenumber());
            ps.setInt(5, 0);
            ps.setString(6, cuss.getUsername());
            ps.setString(7, cuss.getPassword());
            ps.setString(8, cuss.getGender());
            ps.setInt(9, 1);
            ps.setString(10, cuss.getStatuses());
            ps.executeUpdate();
           
	}
        catch (SQLException ex) {
            System.out.println("Insert customer error!" + ex.getMessage());
        }
        return null;
    }
    
    public boolean checkUPCus(String username ){
        
        String sql = " SELECT  username FROM Customer ";
                sql += " WHERE username like ? ";   
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setString(1, username);
 
            ResultSet rs = ps.executeQuery();
                
                if (rs.next()){
                    
                    String TK = rs.getString("username");
                    
                    if(TK != null){
                        return false;
                    }
                    
                }
            
	}
        catch (SQLException ex) {
            System.out.println("Insert customer error!" + ex.getMessage());
        }
        return true;
    }
    
    public boolean checkChnage(String username , String password , String idCuss ){
        
        String sql = " SELECT  username , password FROM Customer ";
               sql += " WHERE username like ? AND password like ? AND customer_id = ? ";
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, Integer.parseInt(idCuss));
                    
            ResultSet rs = ps.executeQuery();
                
                if (rs.next()){
                    
                    String TK = rs.getString("username");
                    String MK = rs.getString("password");
                    
                    if(TK != null && MK != null){
                        return true;
                    }  
                }
	}
        catch (SQLException ex) {
            System.out.println("Insert customer error!" + ex.getMessage());
        }
        return false;
    }
    
    public boolean update(CustomerDTO customer){
        
        String sql = " UPDATE customer ";
               sql+= " SET customer_name = ? , customer_address = ? , phonenumber = ? , "
                        + " username = ? , password = ? , gender = ? , admin_id = ? , statuses = ? ";
               sql+= " WHERE customer_id = ? ";
        
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setString(1, customer.getCustomer_name());
            ps.setString(2, customer.getCustomer_address());
            ps.setString(3, customer.getPhonenumber());
            ps.setString(4, customer.getUsername());
            ps.setString(5, customer.getPassword());
            ps.setString(6, customer.getGender());
            ps.setInt(7, 1);
            ps.setString(8, customer.getStatuses());
            ps.setInt(9, customer.getCustomer_id());
            
            
            ps.executeUpdate();
            
            return true;

            
	}
        catch (SQLException ex) {
            System.out.println("Update customer error!" + ex.getMessage());
        }
        
        return false;
    }
    
    
    
    public boolean deletecustomer(String idC){
        String sql = " DELETE FROM Customer ";               
                sql+= " WHERE customer_id = ? "; 
                
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            int cuss_id = Integer.parseInt(idC);
            
            ps.setInt(1, cuss_id);
            
            ps.executeUpdate();
            
            return true;
           
	}
        catch (SQLException ex) {
            System.out.println("Insert Student error!" + ex.getMessage());
        }
        return false;
    }
    
    
    public boolean updateAddressAndPhone(CustomerDTO customer, String adrress, String phone){
        
        String sql = " UPDATE customer ";
               sql+= " SET customer_name = ? , customer_address = ? , phonenumber = ? , "
                        + " username = ? , password = ? , gender = ? , admin_id = ? ";
               sql+= " WHERE customer_id = ? ";
        
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            
            
            ps.setString(1, customer.getCustomer_name());
            ps.setString(2, adrress);
            ps.setString(3, phone);
            ps.setString(4, customer.getUsername());
            ps.setString(5, customer.getPassword());
            ps.setString(6, customer.getGender());
            ps.setInt(7, 1);
            ps.setInt(8, customer.getCustomer_id());
            
            ps.executeUpdate();
            
            return true;

            
	}
        catch (SQLException ex) {
            System.out.println("Update Address and Phone customer error!" + ex.getMessage());
        }
        
        return false;
    }
    
    public boolean updatefordelete(CustomerDTO customer , String customer_idcheck){
        
        String sql = " UPDATE customer ";
               sql+= " SET customer_name = ? , customer_address = ? , phonenumber = ? , "
                        + " username = ? , password = ? , gender = ? , admin_id = ? , statuses = ? ";
               sql+= " WHERE customer_id = ? ";
        
        try {
            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);                      
            
            ps.setString(1, customer.getCustomer_name());
            ps.setString(2, customer.getCustomer_address());
            ps.setString(3, customer.getPhonenumber());
            ps.setString(4, customer.getUsername());
            ps.setString(5, customer.getPassword());
            ps.setString(6, customer.getGender());
            ps.setInt(7, 1);
            ps.setString(8, "inactive");
            ps.setInt(9, customer.getCustomer_id());
            
            
            ps.executeUpdate();
            
            return true;

            
	}
        catch (SQLException ex) {
            System.out.println("Update customer error!" + ex.getMessage());
        }
        
        return false;
    }
    
}
