package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isami
 */
public class Order {
    
    private int id;
    private Customer customer;
    private List<CartProduct> productList = new ArrayList<>();
    private Timestamp ordered;
    private Timestamp updated;

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<CartProduct> productList) {
        this.productList = productList;
    }

    public Timestamp getOrdered() {
        return ordered;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
    
    
}
