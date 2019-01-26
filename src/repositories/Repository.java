package repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isami
 */
public class Repository {
    
    
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
 
        try (FileInputStream f = new FileInputStream("db.properties")) {
 
            // load the properties file
            Properties pros = new Properties();
            pros.load(f);
 
            // assign db parameters
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static String callAddToCart(int customerId, int shoeId){
        String sql = "call addToCart(?,?)";
        String errorMessage = null;
        ResultSet rs = null;
        
        try(Connection conn = getConnection();
                CallableStatement cstmt = conn.prepareCall(sql)){
            cstmt.setInt(1, customerId);
            cstmt.setInt(2, shoeId);
            cstmt.execute();
            rs = cstmt.getResultSet();
            if(rs != null){
                while(rs.next()){
                    errorMessage = rs.getString(1);
                }
            }            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(rs != null) rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return errorMessage;
    }
}
