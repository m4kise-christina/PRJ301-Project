/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy
 */
public class DAOCustomers extends DBConnection {
    
    public boolean checkLogin2(String username, String password) {  //LOGIN BOOLEAN return true/false.
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM Customers WHERE Username = ? AND Password = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                isValid = true;
            } else {
                isValid = false;
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, e);
        }
        return isValid;
    }
    public Vector<Customers> getAll() {
        Vector<Customers> vector = new Vector<Customers>();
        String sql = "select * from Customers";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String address = rs.getString(5);
                String zip_code = rs.getString(6);
                String username = rs.getString(7);
                String password = rs.getString(8);
                Customers cus = new Customers(id, name, phone, email, address, zip_code, username, password);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
//    public int getCustomerID(String CustomerUsername) {
//        int id = -1;
//        try {
//            String sql = "SELECT * FROM Customers WHERE email = ?";
//            PreparedStatement pre = con.prepareStatement(sql);
//            pre.setString(1, CustomerUsername);
//            ResultSet rs = pre.executeQuery();
//            if (rs.next()) //CURSOR Problem without next()
//            {
//                id = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
//    }

    public int generateID() {
        int id = -1;
        try {
            String sql = "SELECT count(*) FROM Customers";
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) //CURSOR Problem without next()
            {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        id++;
        return id;
    }
    
    public int addCustomers(Customers cus) {
        int n = 0;
        String sql = "INSERT INTO [Customers]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerName]\n"
                + "           ,[Phone]\n"
                + "           ,[Email]\n"
                + "           ,[Address]\n"
                + "           ,[Zip_code]\n"
                + "           ,[Username]\n"
                + "           ,[Password])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, cus.getCustomer_id());
            pre.setString(2, cus.getCustomer_name());
            pre.setString(3, cus.getPhone());
            pre.setString(4, cus.getEmail());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getZip_code());
            pre.setString(7, cus.getUsername());
            pre.setString(8, cus.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    public int getIDbyUsername(String username){
        String sql="select CustomerID from Customers where Username='" + username + "'";
        ResultSet rs = getData(sql);
        int n=0;
        try {
            if (rs.next()) {
                n=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        DAOCustomers dao=new DAOCustomers();
        //System.out.println(dao.generateID());
        System.out.println(dao.getIDbyUsername("sussybaka"));
    }
}