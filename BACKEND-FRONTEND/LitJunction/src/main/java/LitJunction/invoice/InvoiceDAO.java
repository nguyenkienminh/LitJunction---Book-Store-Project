/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.invoice;

/**
 *
 * @author LENOVO
 */

import LitJunction.addtocart.Cart;
import LitJunction.addtocart.Item;
import LitJunction.customer.CustomerDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import LitJunction.utils.DBUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvoiceDAO {
    
    public void addInvoice (CustomerDTO cus, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            //add vao bang Invoice
            String sql = "INSERT INTO [Invoice] VALUES (? , ? , ? , ? ) ";
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setFloat(2, cart.getTotalMoney());
            ps.setInt(3, cus.getCustomer_id());
            ps.setString(4, "order successfully");
            ps.executeUpdate();
            
            String sql1 = "SELECT top 1 invoice_id from [Invoice] order by invoice_id desc ";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs = ps1.executeQuery();
            //add vao bang Invoicedetail
            if(rs.next()) {
                int invoice_id = rs.getInt(1);
                for(Item i: cart.getItems()) {
                    String sql2 = "INSERT INTO [Invoice_detail] VALUES (? , ? , ? , ? ) ";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1, invoice_id);
                    ps2.setInt(2, i.getBook().getBook_id());
                    ps2.setInt(3, i.getQuantity());
                    ps2.setFloat(4, i.getPrice());
                    ps2.executeUpdate();
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Insert Invoice error!" + ex.getMessage());
        }

    }
    
    public List<InvoiceDTO> getAllInvoice() {
        List<InvoiceDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Invoice";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                InvoiceDTO invoice = new InvoiceDTO(
                                rs.getInt("invoice_id"), rs.getString("date"), 
                                rs.getFloat("total_amount"), rs.getInt("customer_id"), rs.getString("buy_status")
                                
                        );
                list.add(invoice);
            }
        } catch(Exception ex) {
            System.out.println("Error getAllInvoice!" + ex.getMessage());
        }
        
        return list;
    }
    
    public InvoiceDTO getInvoiceById(int id) {
        String sql = "SELECT * FROM Invoice WHERE invoice_id = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new InvoiceDTO(id, rs.getString("date"), 
                                rs.getFloat("total_amount"), rs.getInt("customer_id"), rs.getString("buy_status"));
            }
            
        } catch (Exception ex) {
            System.out.println("Error getInvoiceById!" + ex.getMessage());
        }
        return null;
    }
    
    public List<InvoiceDTO> getAllInvoiceByIdCus(int id_cus) {
        List<InvoiceDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Invoice WHERE customer_id = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_cus);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                InvoiceDTO invoice = new InvoiceDTO(
                                rs.getInt("invoice_id"), rs.getString("date"), 
                                rs.getFloat("total_amount"), id_cus, rs.getString("buy_status"));
                list.add(invoice);
            }
        } catch(Exception ex) {
            System.out.println("Error getAllInvoice!" + ex.getMessage());
        }
        return list;
    }
    
    public List<InvoiceDTO> listSearchInvoiceCus(String dateS){ // ngay
        
        List<InvoiceDTO> invoicelist = new ArrayList<InvoiceDTO>();
        
        String sql = " SELECT invoice_id , total_amount , customer_id , buy_status  FROM Invoice ";
                sql += " WHERE date like ? ";
            
 
        try {
                Connection con = DBUtils.getConnection();            
                             
                PreparedStatement stmt = con.prepareStatement(sql);
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = sdf.parse(dateS);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                
                stmt.setDate(1,sqlDate);
                               
                ResultSet rs = stmt.executeQuery();
                
                    if(rs != null){
                        while (rs.next()){
                            int invoice = rs.getInt("invoice_id");
                            String date = getDateString(invoice);
                            int customer_id = rs.getInt("customer_id");
                            float total_amount = rs.getFloat("total_amount");
                            String buy_status = rs.getString("buy_status");
                            invoicelist.add(new InvoiceDTO(invoice, date, total_amount, customer_id, buy_status));
                        }
                        return invoicelist;
                    }
                 con.close();
                               
            } catch (SQLException ex) {                
                System.out.println("Error in servlet. Details:" + ex.getMessage());
                ex.printStackTrace();
                
            } catch (ParseException ex) {
            Logger.getLogger(InvoiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    public String getDateString(int invoice){
        
            String sql = " SELECT date FROM Invoice " +  
                         " WHERE invoice_id = ? ";
        
        try {
            
            Connection con = DBUtils.getConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, invoice);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Date date = rs.getDate(1);
                SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd ");
                String formattedDate = sdf.format(date);
                return formattedDate;
            }
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean deleteInvoiceById(int invoice_id){                                     
        try {
            String sql = "DELETE FROM Invoice_detail WHERE invoice_id = ? ";
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);                      
            ps.setInt(1, invoice_id);
            ps.executeUpdate();
            
            String sql1 = "DELETE FROM Invoice WHERE invoice_id = ? ";
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, invoice_id);
            ps1.executeUpdate();
            return true;
           
	}
        catch (SQLException ex) {
            System.out.println("Delete Invoice error!" + ex.getMessage());
        }
        return false;
    }
    
    public int getInvoiceNewCreate() {
        String sql = "SELECT top 1 invoice_id FROM Invoice ORDER BY invoice_id DESC ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("invoice_id");
            }
            
        } catch (Exception ex) {
            System.out.println("Error getInvoiceNewCreate!" + ex.getMessage());
        }
        return -1;
    }
    
    public List<InvoiceDTO> getAllInvoiceByIdCusDesc(int id_cus) {
        List<InvoiceDTO> list = new ArrayList<>();
        String sql = " SELECT * FROM Invoice WHERE customer_id = ? ORDER BY invoice_id DESC ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_cus);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                InvoiceDTO invoice = new InvoiceDTO(
                                rs.getInt("invoice_id"), rs.getString("date"), 
                                rs.getFloat("total_amount"), id_cus, rs.getString("buy_status"));
                list.add(invoice);
            }
        } catch(Exception ex) {
            System.out.println("Error getAllInvoice!" + ex.getMessage());
        }
        return list;
    }
    
    
}
