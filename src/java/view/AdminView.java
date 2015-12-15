/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AdminController;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Customer;

/**
 *
 * @author guuurris
 */
@SessionScoped
@Named("adminView")
public class AdminView implements Serializable{
   
    private boolean signedIn;

    
    private String userID;

   
    private String password;
    private boolean ban;
    
    private String productID;
    private int product_units;
    
    
    @EJB
    AdminController cont;
    
    public AdminView() {
        signedIn = false;
        cont = new AdminController();
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
    
  
    
     public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getProduct_units() {
        return product_units;
    }

    public void setProduct_units(int product_units) {
        this.product_units = product_units;
    }
    public void login(){
        signedIn = cont.login(userID, password);  
       if(!signedIn){
           //lägger till felmeddelande som kan ses i jsf filen
           FacesContext.getCurrentInstance().addMessage("loginCredentials",
                new FacesMessage(FacesMessage.SEVERITY_WARN, "login failed", "Admin credentials does not work"));
       }
       
    }
    
    public void customerBann(){
       cont.customerBann(userID, ban);
    }
    
    
    public void addProduct(){
        cont.addProduct(productID, product_units);
    }
    
    public void removeProduct(){
        cont.removeProduct(productID);
    }
    
    public void addProductUnits(){
        cont.addProductUnits(productID, product_units);
    }
    
    public List<Customer> getCustomers(){
        return cont.listCustomer();
    }
    public void logout(){
        signedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
