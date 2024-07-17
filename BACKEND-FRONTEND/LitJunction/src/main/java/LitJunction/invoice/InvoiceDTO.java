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
public class InvoiceDTO {
    
    private int invoice_id;
    private String date;
    private float total_amount;
    private int customer_id;
    private String buy_status;

    public InvoiceDTO() {
    }

    public InvoiceDTO(int invoice_id, String date, float total_amount, int customer_id, String buy_status) {
        this.invoice_id = invoice_id;
        this.date = date;
        this.total_amount = total_amount;
        this.customer_id = customer_id;
        this.buy_status = buy_status;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getBuy_status() {
        return buy_status;
    }

    public void setBuy_status(String buy_status) {
        this.buy_status = buy_status;
    }
    
    
    
}
