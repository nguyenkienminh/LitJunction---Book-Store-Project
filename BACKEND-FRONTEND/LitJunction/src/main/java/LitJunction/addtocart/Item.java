/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.addtocart;

import LitJunction.book.BookDTO;

/**
 *
 * @author LENOVO
 */
public class Item {
    private BookDTO book;
    private int quantity;
    private float price; //gia ban khac gia trong san pham
    private String buy_status;

    public Item() {
    }

    public Item(BookDTO book, int quantity, float price) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
}
