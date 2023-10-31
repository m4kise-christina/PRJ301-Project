/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Sy
 */
public class Orders {
    private int OrderID;
    private int CustomerID;
    private int OrderStatus;
    private String OrderDate;
    private double Total;
    public Orders() {
    }

    public Orders(int OrderID, int CustomerID, int OrderStatus, String OrderDate, double Total) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.OrderStatus = OrderStatus;
        this.OrderDate = OrderDate;
        this.Total = Total;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    

}