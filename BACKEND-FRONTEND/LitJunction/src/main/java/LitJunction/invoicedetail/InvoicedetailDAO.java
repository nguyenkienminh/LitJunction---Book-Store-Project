/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.invoicedetail;

import LitJunction.invoice.InvoiceDTO;
import LitJunction.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class InvoicedetailDAO {
    
    public List<InvoicedetailDTO> getAllInvoiceDetail() {
        List<InvoicedetailDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Invoice_detail ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                InvoicedetailDTO invoiceDetail = new InvoicedetailDTO(
                                rs.getInt("invoice_id"), rs.getInt("book_id"), 
                                rs.getInt("buy_quantity"), rs.getFloat("price"), rs.getString("title")
                        );
                list.add(invoiceDetail);
            }
        } catch(Exception ex) {
            System.out.println("Error getAllInvoiceDetail!" + ex.getMessage());
        }
        
        return list;
    }
    
    public List<InvoicedetailDTO> getInvoiceDetailById(int id) {
        List<InvoicedetailDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Invoice_detail iDetail INNER JOIN Book b ON\n" +
                    "  iDetail.book_id = b.book_id WHERE invoice_id = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                InvoicedetailDTO invoiceDetail = new InvoicedetailDTO(
                                id, rs.getInt("book_id"), 
                                rs.getInt("buy_quantity"), rs.getFloat("price"), rs.getString("title"));
                list.add(invoiceDetail);
            }
        } catch(Exception ex) {
            System.out.println("Error getAllInvoiceDetail!" + ex.getMessage());
        }
        return list;
    }
    
}
