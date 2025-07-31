package hotel.booking.system;
import java.sql.*;
//Conn class that is typically used to connect your Java application to a MySQL database
public class Conn {
    
    Connection c; //It represents the connection between your Java app and the MySQL database.
    Statement s; //It is used to send SQL commands (like SELECT, INSERT, etc.) to the database.
    
    Conn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///hotelbookingsystem","root","Prachi@123");
            s = c.createStatement();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }   
}
