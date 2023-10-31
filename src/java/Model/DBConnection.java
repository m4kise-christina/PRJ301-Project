/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy
 */
public class DBConnection {
    public Connection con=null;
    public DBConnection(String url, String username, String password){
        try {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            con=DriverManager.getConnection(url,username,password);
            System.out.println("connected");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getData(String sql){
        ResultSet rs = null;
        Statement state;
        try {
            state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            // run and get result
        return rs;
    }
    public DBConnection(){
//        try {
//            //call driver
//            try {
//                //connection
//                con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SE1741", "sa", "sinhvinh123");
//                System.out.println("connected");
//            } catch (SQLException ex) {
//                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
        this("jdbc:sqlserver://localhost:1433;databaseName=Project", "sa", "123456");
    }
    public static void main(String[] args) {
           // TODO code application logic here
           DBConnection obj=new DBConnection();
    }
}
