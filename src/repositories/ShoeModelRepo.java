package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
/**
 *
 * @author isami
 */
public class ShoeModelRepo {
    
    public static ShoeModel getShoeModelById(int modelid){
        String sql = "select * from shoemodels where id = ?";
        ShoeModel shoemodel = null;
        ResultSet rs = null;
        
        try(Connection conn = Repository.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, modelid);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                short year = rs.getShort(3);
                shoemodel = new ShoeModel(id, name, year);
                shoemodel.setColors(getShoeModelColors(id));
                shoemodel.setBrands(getShoeModelBrands(id));
                shoemodel.setCategories(getShoeModelCategories(id));
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
        
        return shoemodel;
    }
    
    public static List<Color> getShoeModelColors(int modelid){
        String sql = "select colors.id, colors.name from shoemodels_colors "
                + "inner join colors on shoemodels_colors.color_id = colors.id where shoemodel_id = ?";
        List<Color> colors = new ArrayList<>();
        ResultSet rs = null;
        
        try(Connection conn = Repository.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, modelid);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Color color = new Color(id, name);
                colors.add(color);
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
        
        return colors;
    }
    
    public static List<Brand> getShoeModelBrands(int modelid){
        String sql = "select brands.id, brands.name from shoemodels_brands "
                + "inner join brands on shoemodels_brands.brand_id = brands.id where shoemodel_id = ?";
        List<Brand> brands = new ArrayList<>();
        ResultSet rs = null;
        
        try(Connection conn = Repository.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, modelid);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Brand brand = new Brand(id, name);
                brands.add(brand);
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
        
        return brands;
    }
    
    public static List<Category> getShoeModelCategories(int modelid){
        String sql = "select categories.id, categories.name from shoemodels_categories "
                + "inner join categories on shoemodels_categories.category_id = categories.id where shoemodel_id = ?";
        List<Category> categories = new ArrayList<>();
        ResultSet rs = null;
        
        try(Connection conn = Repository.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, modelid);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Category category = new Category(id, name);
                categories.add(category);
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
        
        return categories;
    }
    
    public static Map<Integer, ShoeModel> getAllShoeModelsAsMap(){
        String sql = "select * from shoemodels";
        Map<Integer, ShoeModel> shoemodelMap = new HashMap<>();
        
        try(Connection conn = Repository.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet  rs = stmt.executeQuery(sql)){
            int count = 1;
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                short year = rs.getShort(3);
                ShoeModel shoemodel = new ShoeModel(id, name, year);
                shoemodel.setColors(getShoeModelColors(id));
                shoemodel.setBrands(getShoeModelBrands(id));
                shoemodel.setCategories(getShoeModelCategories(id));
                shoemodelMap.put(count++, shoemodel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return shoemodelMap;
    }
    
    
}
