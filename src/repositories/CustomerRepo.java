package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import models.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isami
 */
public class CustomerRepo {
    
    public static List<Customer> getAllCustomers(){
        
        String sql = "select * from customers";
        List<Customer> customerlist = new ArrayList<>();
        
        try(Connection conn = Repository.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt(1);
                String firstname = rs.getString(2);
                String lastname = rs.getString(3);
                String personalnumber = rs.getString(4);
                int postcode = rs.getInt(5);
                String address = rs.getString(6);
                String city = rs.getString(7);
                Customer customer = new Customer(id, firstname, lastname, 
                        personalnumber, postcode, address, city);
                customerlist.add(customer);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customerlist;
    }
    
    public static Map<Integer, Customer> getAllCustomersAsMap(){
        
        String sql = "select * from customers";
        Map<Integer, Customer> customerMap = new HashMap<>();
        
        try(Connection conn = Repository.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            
            int count = 1;
            while(rs.next()){
                int id = rs.getInt(1);
                String firstname = rs.getString(2);
                String lastname = rs.getString(3);
                String personalnumber = rs.getString(4);
                int postcode = rs.getInt(5);
                String address = rs.getString(6);
                String city = rs.getString(7);
                Customer customer = new Customer(id, firstname, lastname, 
                        personalnumber, postcode, address, city);
                customerMap.put(count, customer);
                count++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return customerMap;
    }
    
    public static Customer getCustomerById(int customerId){
        String sql = "select * from customers where id = ?";
        Customer customer = null;
        ResultSet rs = null;
        
        try(Connection conn = Repository.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, customerId);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                String firstname = rs.getString(2);
                String lastname = rs.getString(3);
                String personalnumber = rs.getString(4);
                int postcode = rs.getInt(5);
                String address = rs.getString(6);
                String city = rs.getString(7);
                customer = new Customer(id, firstname, lastname, 
                        personalnumber, postcode, address, city);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(rs != null) rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return customer;
    }
}
