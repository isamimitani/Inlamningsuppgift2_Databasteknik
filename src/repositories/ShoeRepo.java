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
public class ShoeRepo {
    
    public static List<Shoe> getAllShoesByJoin(){
        String sql = "select shoes.id, shoes.size, shoes.price, shoes.amount,\n" +
                "shoes.shoemodel_id, shoemodels.name, shoemodels.year\n" +
                "from shoes\n" +
                "inner join shoemodels on shoes.shoemodel_id = shoemodels.id\n" +
                "inner join shoemodels_brands on shoemodels.id = shoemodels_brands.shoemodel_id\n" +
                "order by shoes.id";
        List<Shoe> shoelist = new ArrayList<>();
        
        try(Connection conn = Repository.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt(1);
                float size = rs.getFloat(2);
                float price = rs.getFloat(3);
                int amount = rs.getInt(4);
                int modelId = rs.getInt(5);
                String modelname = rs.getString(6);
                short year = rs.getShort(7);
                ShoeModel shoemodel = new ShoeModel(modelId, modelname, year);
                Shoe  shoe = new Shoe(id, size, price, amount, shoemodel);
                shoelist.add(shoe);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return shoelist;
    }
    
    public static List<Shoe> getAllShoes(){
        String sql = "select shoes.id, shoes.size, shoes.price, shoes.amount,\n" +
                "shoes.shoemodel_id from shoes\n" +
                "order by shoes.id";
        List<Shoe> shoelist = new ArrayList<>();
        
        try(Connection conn = Repository.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt(1);
                float size = rs.getFloat(2);
                float price = rs.getFloat(3);
                int amount = rs.getInt(4);
                ShoeModel shoemodel = ShoeModelRepo.getShoeModelById(rs.getInt(5));
                Shoe  shoe = new Shoe(id, size, price, amount, shoemodel);
                shoelist.add(shoe);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return shoelist;
    }
    
    public static Shoe getShoeById(int shoeId){
        String sql = "select * from shoes where id = ?";
        Shoe shoe = null;
        ResultSet rs = null;
        
        try(Connection conn = Repository.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, shoeId);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                float size = rs.getFloat(2);
                float price = rs.getFloat(3);
                int amount = rs.getInt(4);
                ShoeModel shoemodel = ShoeModelRepo.getShoeModelById(rs.getInt(5));
                shoe = new Shoe(id, size, price, amount, shoemodel);
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
        
        return shoe;
    }
    
    public static Map<Integer, Shoe> getShoesByShoeModelId(int shoemodelId){
        String sql = "select * from shoes where shoemodel_id = ?";
        Shoe shoe = null;
        ResultSet rs = null;
        Map<Integer, Shoe> shoeMap = new HashMap<>();
        
        try(Connection conn = Repository.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, shoemodelId);
            rs = pstmt.executeQuery();
            
            int count = 1;
            while(rs.next()){
                int id = rs.getInt(1);
                float size = rs.getFloat(2);
                float price = rs.getFloat(3);
                int amount = rs.getInt(4);
                ShoeModel shoemodel = ShoeModelRepo.getShoeModelById(rs.getInt(5));
                shoe = new Shoe(id, size, price, amount, shoemodel);
                shoeMap.put(count++, shoe);
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
        return shoeMap;
    }
}
