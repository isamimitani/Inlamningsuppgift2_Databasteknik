package models;

import java.sql.Timestamp;

/**
 *
 * @author isami
 */
public class CartProduct {
    
    private int id;
    private Customer customer;
    private Shoe shoe;
    private int amount;
    private boolean isOrdered;
    private Timestamp added;
    private Timestamp updated;

    public CartProduct(int id, Customer customer, Shoe shoe, int amount, boolean isOrdered) {
        this.id = id;
        this.customer = customer;
        this.shoe = shoe;
        this.amount = amount;
        this.isOrdered = isOrdered;
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

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(boolean isOrdered) {
        this.isOrdered = isOrdered;
    }

    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
    
    
}
