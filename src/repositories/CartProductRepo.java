package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;


/**
 *
 * @author isami
 */
public class CartProductRepo {
    
    public static List<CartProduct> getCartProductsByCustomerId(int customerid){
        String sql = "select * from cart_products where customer_id = ? and is_ordered = false";
        List<CartProduct> cartList = new ArrayList<>();
        ResultSet rs = null;
        
        try(Connection conn = Repository.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, customerid);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                Customer customer = CustomerRepo.getCustomerById(rs.getInt(2));
                Shoe shoe = ShoeRepo.getShoeById(rs.getInt(3));
                int amount = rs.getInt(4);
                boolean isOrdered = rs.getBoolean(5);
                CartProduct product = new CartProduct(id, customer, shoe, amount, isOrdered);
                cartList.add(product);
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
        return cartList;
    }
    
    public static Order createOrder(Customer customer){
        List<CartProduct> productList = new ArrayList<>();
        Order order = null;
        String sql1 = "select * from cart_products where customer_id = ? and is_ordered = false";
        String sql2 = "insert into orders(customer_id) values(?)";
        String sql3 = "insert into orders_products(order_id, cart_product_id) values(?,?)";
        String sql4 = "update cart_products set is_ordered = true where customer_id = ? and is_ordered = false"; 
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        PreparedStatement pstmt4 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int rowCount = 0;
        int lastId = 0;
                       
        try{
            conn = Repository.getConnection();
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt3 = conn.prepareStatement(sql3);
            pstmt4 = conn.prepareStatement(sql4);
            
            pstmt1.setInt(1, customer.getId());
            rs1 = pstmt1.executeQuery();
            while(rs1.next()){
                int id = rs1.getInt(1);
                Shoe shoe = ShoeRepo.getShoeById(rs1.getInt(3));
                int amount = rs1.getInt(4);
                boolean isOrdered = rs1.getBoolean(5);
                CartProduct product = new CartProduct(id, customer, shoe, amount, isOrdered);
                productList.add(product);
            }
            if(!productList.isEmpty()){
                conn.setAutoCommit(false);
                pstmt2.setInt(1, customer.getId());
                rowCount = pstmt2.executeUpdate();
                if(rowCount != 1){
                    conn.rollback();
                    return order;
                }
                rs2 = pstmt2.getGeneratedKeys();
                while(rs2.next()){
                    lastId = rs2.getInt(1);
                }
                for(CartProduct p : productList){
                    pstmt3.setInt(1, lastId);
                    pstmt3.setInt(2, p.getId());
                    rowCount = pstmt3.executeUpdate();
                    if(rowCount != 1){
                        conn.rollback();
                        return order;
                    }
                }
                pstmt4.setInt(1, customer.getId());
                rowCount = pstmt4.executeUpdate();
                if(rowCount < 1){
                    conn.rollback();
                    return order;
                }
                
                conn.commit();
                //conn.setAutoCommit(true);
                
                order = new Order(lastId, customer);
                order.setProductList(productList);
                return order;               
            }            
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CartProductRepo.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(CartProductRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(rs2 != null) rs2.close();
                if(rs1 != null) rs1.close();
                if(pstmt3 != null) pstmt3.close();
                if(pstmt2 != null) pstmt2.close();
                if(pstmt1 != null) pstmt1.close();
                if(conn != null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CartProductRepo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        return order;
    }
}
