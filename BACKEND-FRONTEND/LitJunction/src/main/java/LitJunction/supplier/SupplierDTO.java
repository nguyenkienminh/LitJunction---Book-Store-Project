/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LitJunction.supplier;

import LitJunction.category.*;
import LitJunction.book.*;
import LitJunction.admin.*;
import LitJunction.customer.*;


public class SupplierDTO {
    

    private int supplier_id;
    private String supplier_name;
    private String supplier_address;
    private int admin_id;

    public SupplierDTO(int supplier_id, String supplier_name, String supplier_address, int admin_id) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.supplier_address = supplier_address;
        this.admin_id = admin_id;
    }

    public SupplierDTO() {
        
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    
    
    

    
    
    


    



    

    

}
