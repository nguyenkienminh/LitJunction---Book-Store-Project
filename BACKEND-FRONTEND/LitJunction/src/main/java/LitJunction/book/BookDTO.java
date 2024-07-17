/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LitJunction.book;

import LitJunction.admin.*;
import LitJunction.customer.*;


public class BookDTO {
    
    private int book_id;
    private String book_image;
    private String title;
    private String author;
    private float price;
    private String describe;
    private int bookstore_quantity;
    private int admin_id; 
    private int category_id;
    private int supplier_id;

    public BookDTO(int book_id, String book_image, String title, String author, float price, String describe, int bookstore_quantity, int admin_id, int category_id, int supplier_id) {
        this.book_id = book_id;
        this.book_image = book_image;
        this.title = title;
        this.author = author;
        this.price = price;
        this.describe = describe;
        this.bookstore_quantity = bookstore_quantity;
        this.admin_id = admin_id;
        this.category_id = category_id;
        this.supplier_id = supplier_id;
    }

    public BookDTO() {
    
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getBookstore_quantity() {
        return bookstore_quantity;
    }

    public void setBookstore_quantity(int bookstore_quantity) {
        this.bookstore_quantity = bookstore_quantity;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }
    
    


    



    

    

}
