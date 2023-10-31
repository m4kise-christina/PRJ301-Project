/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy
 */
public class DAOProduct extends DBConnection {

    public Vector<Product> getAll() {
        Vector<Product> vector = new Vector<Product>();
        String sql = "select * from Product";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int pid = rs.getInt(1);
                String pname = rs.getString(2);
                double price = rs.getDouble(3);
                int modelyear = rs.getInt(4);
                String brandname = rs.getString(5);
                String category = rs.getString(6);
                Product pro = new Product(pid, pname, price, modelyear, brandname, category);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Product> findByName(String name) {
        Vector<Product> vector = new Vector<Product>();
        String sql = "select * from Product where ProductName like '%" + name + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int pid = rs.getInt(1); //int id=rs.getInt(1);
                String pname = rs.getString(2);
                double price = rs.getDouble(3);
                int modelyear = rs.getInt(4);
                String brandname = rs.getString(5);
                String category = rs.getString(6);
                Product pro = new Product(pid, pname, price, modelyear, brandname, category);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Product> findByCate(String name) {
        Vector<Product> vector = new Vector<Product>();
        String sql = "select * from product where Category='" + name + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String pname = rs.getString(2); //String name=rs.getString("product_name");
                double price = rs.getDouble(3);
                int year = rs.getInt(4);
                String brand = rs.getString(5);
                String cate = rs.getString(6);
                Product pro = new Product(id, pname, price, year, brand, cate);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public ResultSet getAllCategories() {
        String sql = "select distinct Category from Product";
        ResultSet rs = getData(sql);
        return rs;
    }

    public int addProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [product]\n"
                + "           ([productID]\n"
                + "           ,[productName]\n"
                + "           ,[price]\n"
                + "           ,[modelyear]\n"
                + "           ,[brandname]\n"
                + "           ,[category])\n"
                + "     VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //set parameter: pre.setDataType(index,para);
            // index start from 1
            pre.setInt(1, pro.getProductID());
            pre.setString(2, pro.getProductName());
            pre.setDouble(3, pro.getPrice());
            pre.setInt(4, pro.getModelYear());
            pre.setString(5, pro.getBrandName());
            pre.setString(6, pro.getCategory());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [Product]\n"
                + "   SET [ProductName] = ?"
                + "      ,[price] = ?"
                + "      ,[modelyear] = ?"
                + "      ,[brandname] = ?"
                + "      ,[category] = ?"
                + " WHERE [productID]=?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, pro.getProductName());
            pre.setDouble(2, pro.getPrice());
            pre.setInt(3, pro.getModelYear());
            pre.setString(4, pro.getBrandName());
            pre.setString(5, pro.getCategory());
            pre.setInt(6, pro.getProductID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int generateID() {
        int id = -1;
        try {
            String sql = "select max(ProductID) from Product";
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) //CURSOR Problem without next()
            {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        id++;
        return id;
    }

    public Product findByID(int pid) {
        Product pro = null;
        String sql = "select * from product where productID = " + pid;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String pname = rs.getString(2);
                double price = rs.getDouble(3);
                int modelyear = rs.getInt(4);
                String brand = rs.getString(5);
                String category = rs.getString(6);
                pro = new Product(id, pname, price, modelyear, brand, category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

    public int removeProduct(int id) {
        int n = 0;
        //check foreign key value
        try {
            Statement statement = con.createStatement();
//            statement.executeUpdate("delete from Inventory where productID="+id);
//            statement.executeUpdate("delete from OrderDetail where productID="+id);
//            statement.executeUpdate("delete from Discount where productID="+id);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rsInventory = getData("select * from Inventory where productID=" + id);
        ResultSet rsOrderItem = getData("select * from OrderDetail where productID=" + id);
        try {
            if (!(rsOrderItem.next() && rsInventory.next())) {
                Statement state = con.createStatement();
                n = state.executeUpdate("delete from Product where productID=" + id);
            }
            else{
                return 0;
            }
        } catch (SQLException ex) {
            n = -1;//loi cau lenh
        }
        return n;
    }

//    public double getRawPrice(int productID) {
//        String sql = "select Price from Product where ProductID=" + productID;
//        int raw = 0;
//        ResultSet rs = getData(sql);
//        try {
//            if (rs.next()) {
//                raw = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return raw;
//    }
    public double getFinalPrice(int productID) {
        Product pro = findByID(productID);
        double price = pro.getPrice();
        DAODiscount dao=new DAODiscount();
        double percent = (double)dao.getDiscount(productID)/100;
        double finalPrice = price - price * percent;
        return finalPrice;
    }

    public static void main(String[] args) { //test update successful
        DAOProduct dao = new DAOProduct();
        Product pro = new Product(70, "asd", 333, 2222, "asd", "asd");
//        int n=dao.addProduct(pro);
//        System.out.println(n);
//        int n=dao.removeProduct(69);
//        System.out.println(n); 
//        System.out.println(dao.findByID(18));
//        System.out.println(dao.getRawPrice(2));
//        System.out.println(dao.getFinalPrice(2));
    dao.removeProduct(1);
    }
}
