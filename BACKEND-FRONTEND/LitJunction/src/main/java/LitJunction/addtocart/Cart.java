/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.addtocart;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
// chua ds cac item
public class Cart {
    
    private List<Item> items;
    
    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    
    
    // lay ra 1 san pham trong gio
    private Item getItemById(int id) {
        for (Item i : items) {
            if(i.getBook().getBook_id() == id)
                return i;
        }
        return null;
    }
    
    // tra ve so luong by id
    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();       
    }
    
    // them vao gio
    public void addItem(Item b) {
        // th1: co trong gio roi
        if (getItemById(b.getBook().getBook_id()) != null) {
            Item i = getItemById(b.getBook().getBook_id());
            i.setQuantity(i.getQuantity() + b.getQuantity());
        } else {
            // th2: chua co
            items.add(b);
        }
        
    }
    
    public void removeItem(int id) {
        if(getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }
    
    public float getTotalMoney() {
        float t = 0;
        for (Item i : items) {
            t += i.getQuantity() * i.getPrice();
        }
        return t;
    }
    
}
