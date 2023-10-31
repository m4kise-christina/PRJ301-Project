/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;
public class Admin {
    private String useradmin;
    private String password;

    public Admin() {
    }

    public Admin(String useradmin, String password) {
        this.useradmin = useradmin;
        this.password = password;
    }

    public String getUseradmin() {
        return useradmin;
    }

    public void setUseradmin(String useradmin) {
        this.useradmin = useradmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
