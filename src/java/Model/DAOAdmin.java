/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy
 */
public class DAOAdmin extends DBConnection{
    public boolean checkLoginAdmin(String username, String password) {  //LOGIN BOOLEAN return true/false.
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM Admins WHERE useradmin = ? AND password = ?";
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
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, e);
        }
        return isValid;
    }
    public static void main(String[] args) {
        DAOAdmin dao=new DAOAdmin();
//        System.out.println(dao.checkLoginAdmin("admin", "admin"));
    }
}
