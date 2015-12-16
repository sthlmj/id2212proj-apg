
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

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
    
    public RegisterView(){
        this.username = "";
        this.password = "";
        cont = new CustomerController();
    }
    
    public boolean register(){ 

       return success = cont.register(username, password);
    }
    
    
    
}
