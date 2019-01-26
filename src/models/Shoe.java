package models;

import java.sql.Timestamp;

/**
 *
 * @author isami
 */
public class Shoe {
    
    private int id;
    private float size;
    private float price;
    private int amount;
    private ShoeModel shoemodel;
    private Timestamp created;
    private Timestamp updated;

    public Shoe(int id, float size, float price, int amount, ShoeModel shoemodel) {
        this.id = id;
        this.size = size;
        this.price = price;
        this.amount = amount;
        this.shoemodel = shoemodel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ShoeModel getShoemodel() {
        return shoemodel;
    }

    public void setShoemodel(ShoeModel shoemodel) {
        this.shoemodel = shoemodel;
    }
    
    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
