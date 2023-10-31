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

public class DAODiscount extends DBConnection{
    public int getDiscount(int ProductID){
        int dis=-1, n=0;
        String sql="select discount from Discount where ProductID="+ProductID;
        ResultSet rs=getData(sql);
        try {
            if(rs.next())
            dis=rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAODiscount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dis;
    }
    public static void main(String[] args) {
        DAODiscount dao=new DAODiscount();
        System.out.println(dao.getDiscount(15));
    }
}
