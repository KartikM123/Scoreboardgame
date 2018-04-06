package scoreboardgame;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//NOTE CALCULATED STATS NOT READY
/**
 *
 * @author karti
 */
public class scoreboardgame {

    /**
     * Connection  Schema: jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull [root on Default schema]
     * https://netbeans.org/kb/articles/mysql.html#gettingStarted
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
     AppFrame frame = new AppFrame();
     frame.setVisible(true);
        /*  String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mynewdatabase?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "kartik";
        Connection conn = null;
        Statement stmt = null;
        conn = DriverManager.getConnection(url,user,password);
        System.out.println("connection complete");
        stmt = conn.createStatement();
        String sql = "UPDATE Registration " +
                   "SET age = 30 WHERE id in (100, 101)";
        
        // TODO code application logic here*/
    }
    
}
