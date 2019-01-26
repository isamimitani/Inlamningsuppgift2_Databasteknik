package models;

import java.sql.Timestamp;

/**
 *
 * @author isami
 */
public class Review {
    
    private int id;
    private Customer customer;
    private ShoeModel shoemodel;
    private Grade grade;
    private String comments;
    private Timestamp created;
    private Timestamp updated;

    public Review(int id, Customer customer, ShoeModel shoemodel, Grade grade, String comments) {
        this.id = id;
        this.customer = customer;
        this.shoemodel = shoemodel;
        this.grade = grade;
        this.comments = comments;
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

    public ShoeModel getShoemodel() {
        return shoemodel;
    }

    public void setShoemodel(ShoeModel shoemodel) {
        this.shoemodel = shoemodel;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
