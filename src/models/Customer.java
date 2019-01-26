package models;

import java.sql.Timestamp;

/**
 *
 * @author isami
 */
public class Customer {
    
    private int id;
    private String firstName;
    private String lastName;
    private String personalnumber;
    private int postcode;
    private String address;
    private String city;
    private Timestamp created;
    private Timestamp updated;

    public Customer(int id, String firstName, String lastName, 
            String personalnumber, int postcode, String address, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalnumber = personalnumber;
        this.postcode = postcode;
        this.address = address;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalnumber() {
        return personalnumber;
    }

    public void setPersonalnumber(String personalnumber) {
        this.personalnumber = personalnumber;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
