/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LitJunction.admin;

import LitJunction.customer.*;


public class AdminDTO {
    
    private int admin_id;
    private String admin_name;
    private String username_admin;
    private String password_admin;

    public AdminDTO(int admin_id, String name_admin, String username_admin, String password_admin) {
        this.admin_id = admin_id;
        this.admin_name = name_admin;
        this.username_admin = username_admin;
        this.password_admin = password_admin;
    }

    AdminDTO() {
        
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getUsername_admin() {
        return username_admin;
    }

    public void setUsername_admin(String username_admin) {
        this.username_admin = username_admin;
    }

    public String getPassword_admin() {
        return password_admin;
    }

    public void setPassword_admin(String password_admin) {
        this.password_admin = password_admin;
    }

    


    

    

}
