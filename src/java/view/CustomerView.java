/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CustomerController;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author guuurris
 */
@SessionScoped
@Named("customerview")
public class CustomerView implements Serializable{
    @EJB
    private CustomerController cont;
    
    private String username;
    private boolean signedIn;

    
    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
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
    private String password;

    public CustomerView() {
        this.username = "";
        this.password = "";
        cont = new CustomerController();
    }
    
    public boolean login(){
       signedIn = cont.login(username, password);
       if(!signedIn){
           //lägger till felmeddelande som kan ses i jsf filen
           FacesContext.getCurrentInstance().addMessage("loginCredentials",
                new FacesMessage(FacesMessage.SEVERITY_WARN, "login failed", "Your login credentials does not match any user"));
       }
        
        return signedIn;
    }
    public void logout(){
        signedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
}
