
package view;

import controller.CustomerController;
import controller.ShoppingController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import model.Product;

/**
 *
 * @author guuurris
 */
@SessionScoped
@Named("customerview")
public class CustomerView implements Serializable{
    @EJB
    private CustomerController cont;
    
    @EJB
    private ShoppingController cartCont;
    
    //Register and login credential
    private String username;
    private boolean signedIn;
    
    //Shop information
    private String type;
    private int units; 

    private List<Product> cart = new ArrayList();

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
     
        this.cart = cart;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    
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
        signedIn = false;
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
    
    public Product takeProduct(){
        Product p = cartCont.takeProduct(type, units);
        if(p != null){
          cart.add(p);  
        }
        return p;
    }
    
    public void checkoutcart(){
       
        if(cartCont.buy(cart)){//product bought
            FacesContext.getCurrentInstance().addMessage("cart",
                new FacesMessage(FacesMessage.SEVERITY_WARN,"Buy Status", "Product(s) was purchased"));
        }
        else{
             FacesContext.getCurrentInstance().addMessage("cart",
                new FacesMessage(FacesMessage.SEVERITY_WARN,"Buy Status", "Product(s) could not be purchased"));
        }
         setCart(new ArrayList());
    }
    
    public List<Product> allProducts(){
        return cartCont.listProducts();
    }
}
