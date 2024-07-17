/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LitJunction.invoicedetail;

/**
 *
 * @author LENOVO
 */
public class InvoicedetailDTO {
    
    private int invoice_id;
    private int book_id;
    private int buy_quantity;
    private float price;
    private String title;

    public InvoicedetailDTO() {
    }

    public InvoicedetailDTO(int invoice_id, int book_id, int buy_quantity, float price, String title) {
        this.invoice_id = invoice_id;
        this.book_id = book_id;
        this.buy_quantity = buy_quantity;
        this.price = price;
        this.title = title;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBuy_quantity() {
        return buy_quantity;
    }

    public void setBuy_quantity(int buy_quantity) {
        this.buy_quantity = buy_quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
}
