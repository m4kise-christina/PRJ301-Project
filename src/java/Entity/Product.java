/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Sy
 */
public class Product {
    private int productID;
    private String productName;
    private double price;
    private int modelYear;
    private String brandName;
    private String Category;

    public Product() {
    }

    public Product(int productID, String productName, double price, int modelYear, String brandName, String Category) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.modelYear = modelYear;
        this.brandName = brandName;
        this.Category = Category;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", price=" + price + ", modelYear=" + modelYear + ", brandName=" + brandName + ", Category=" + Category + '}';
    }
    
}
