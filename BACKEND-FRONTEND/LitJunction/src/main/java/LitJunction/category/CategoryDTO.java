/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LitJunction.category;

import LitJunction.book.*;
import LitJunction.admin.*;
import LitJunction.customer.*;

/**
 *
 * @author DUNGHUYNH
 */
public class CategoryDTO {
    

    private int category_id;
    private String category_name;

    public CategoryDTO(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public CategoryDTO() {
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    
    

    
    
    


    



    

    

}
