
package MyDb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    
    static Connection con;
    public static Connection getCon(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelDB","root","Jabbour2001");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, "An error occurred while connecting to the database", ex);
            System.out.println("Database connection error: " + ex.getMessage());
        }
    return con;
    }
    
}