/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Sy
 */
public class Customers {
    private int customer_id;
    private String customer_name;
    private String phone;
    private String email;
    private String address;
    private String zip_code;
    private String username;
    private String password;

    public Customers() {
    }

    public Customers(int customer_id, String customer_name, String phone, String email, String address, String zip_code, String username, String password) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.zip_code = zip_code;
        this.username = username;
        this.password = password;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customers{" + "customer_id=" + customer_id + ", customer_name=" + customer_name + ", phone=" + phone + ", email=" + email + ", address=" + address + ", zip_code=" + zip_code + ", username=" + username + ", password=" + password + '}';
    }
    
}