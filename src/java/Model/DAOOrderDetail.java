/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.OrderDetail;
import Entity.Orders;
import Entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy
 */
public class DAOOrderDetail extends DBConnection {

    private final static DAOOrders daoO = new DAOOrders();
    private final static DAOOrderDetail daoOD = new DAOOrderDetail();
    private final static DAOProduct daoP = new DAOProduct();
    private final static DAOInventory daoI = new DAOInventory();

    public int getLastID() {
        int n = 0;
        String sql = "SELECT TOP(1)ID FROM OrderDetail ORDER BY ID DESC;";

        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addOrderDetail(OrderDetail item) {
        int n = 0;
        String sql = "INSERT INTO [OrderDetail]([ID],[OrderID],ProductID,[quantity],[subtotal] )VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //set parameter: pre.setDataType(index,para);
            // index start from 1
            pre.setInt(1, item.getID());
            pre.setInt(2, item.getOrderID());
            pre.setInt(3, item.getProductID());
            pre.setInt(4, item.getQuantity());
            pre.setDouble(5, item.getSubtotal());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    private void alterDB(int orderID, int productID, int quantity) {
        // Get product
        Product pro = daoP.findByID(productID);
        int quant = daoI.getQuantityByID(productID);
        double subtotal = daoP.getFinalPrice(productID) * quantity;
        if (quantity <= quant) {
            OrderDetail item = new OrderDetail(daoOD.getLastID() + 1, orderID, productID, quantity, subtotal); // did NOT check for over quantity
            //pro.setQuantity(quant);

            // update product quantity and add orderitem
            daoOD.addOrderDetail(item);
            daoI.UpdateQuantity(productID, quantity);
        }
    }

    private double getTotalPrice(HashMap<Integer, Integer> cart) {  // TINH TOTAL CUA CART.
        double total = 0;
        DAOProduct daoP = new DAOProduct();
        for (Map.Entry<Integer, Integer> cartItem : cart.entrySet()) {
            total += daoP.getFinalPrice(cartItem.getKey()) * cartItem.getValue();
        }
        return total;
    }

    public void checkout(HashMap<Integer, Integer> cart, int CustomerID) {
        DAOOrders daoO = new DAOOrders();
        if (cart.isEmpty()) { // If cart is empty
            return;
        }
        int lastID = daoO.getLastOrderID(); //lay orderID cuoi cung.
        double total = getTotalPrice(cart); //lay total cua cart
//        ------------

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());// lay current DATE/TIME
        Orders order = new Orders(lastID + 1, CustomerID, 1, timeStamp, total);
//        System.out.println(order);
        daoO.addOrder(order);

        // for each item in the cart, we subtract the quantity from DB and add order_items to DB
        for (Map.Entry<Integer, Integer> cartItem : cart.entrySet()) {
            alterDB(order.getOrderID(), cartItem.getKey(), cartItem.getValue());
        }
        // After user checkout, clear the cart
        cart.clear();
    }

    public static void main(String[] args) {
        DAOOrderDetail dao = new DAOOrderDetail();
        OrderDetail item = new OrderDetail(1, 3, 2, 1, 1700);
//        int n=dao.addOrder(item);
//        int n=dao.addOrderDetail(item);
//        System.out.println(daoOD.getLastID());
    }
}
