/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CustomerController;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author guuurris
 */

@RequestScoped
@Named("registerview")
public class RegisterView {
    
    @EJB
    private CustomerController cont;  
    
    private String username;
    private String password; 

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
    
    public RegisterView(){
        this.username = "";
        this.password = "";
        cont = new CustomerController();
    }
    
    public boolean register(){     
       return cont.register(username, password);
    }
    
    
    
}
