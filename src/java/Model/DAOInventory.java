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
public class DAOInventory extends DBConnection{
    public int getQuantityByID(int productID){
        int n=0;
        String sql="select quantity from Inventory where ProductID="+productID;
        ResultSet rs=getData(sql);
        try {
            if(rs.next())
                n=rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int UpdateQuantity(int productID, int subtract){
        int n=0;
        String sql="update Inventory set Quantity = Quantity-? where ProductID=?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, subtract);
            pre.setInt(2, productID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        DAOInventory daoI=new DAOInventory();
        System.out.println(daoI.getQuantityByID(3));
        System.out.println(daoI.UpdateQuantity(16, 2));
    }
}
