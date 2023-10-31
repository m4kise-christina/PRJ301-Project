/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Entity.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy
 */
public class DAOOrders extends DBConnection{
    public int addOrder(Orders ord) {
        int n = 0;
        String sql ="INSERT INTO [Orders]([OrderID],[CustomerID],[OrderStatus],[OrderDate],[Total]) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //set parameter: pre.setDataType(index,para);
            // index start from 1
            pre.setInt(1, ord.getOrderID());
            pre.setInt(2, ord.getCustomerID());
            pre.setInt(3, ord.getOrderStatus());
            pre.setString(4, ord.getOrderDate());
            pre.setDouble(5, ord.getTotal());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int generateID() {
        int id = -1;
        try {
            String sql = "select max(OrderID) from Orders";
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) //CURSOR Problem without next()
            {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        id++;
        return id;
    }
    public int getLastOrderID() {
        String sqlStatement = "SELECT TOP(1) orderID FROM Orders ORDER BY orderID DESC;";
        int lastID = 0;
        ResultSet rs = getData(sqlStatement);
        try {
            if (rs.next()) { // nếu có bản ghi
                lastID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastID;
    }
    public static void main(String[] args) {
        DAOOrders dao=new DAOOrders();
        Orders or=new Orders(dao.generateID(),2,1,"2022/12/11",2300);
        //int n=dao.addOrder(or);
        System.out.println(dao.getLastOrderID());
    }
}
