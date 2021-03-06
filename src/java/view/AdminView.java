package view;

import controller.AdminController;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Customer;
import model.Product;

/**
 * View - AdminView.java JSF adminarea.xhtml using this to handle interaction
 * with user. Bean scope is a SessionScoped. Named called by JSF is "adminView".
 *
 * @author guuurris
 */
@SessionScoped
@Named("adminView")
public class AdminView implements Serializable {

    private boolean signedIn;

    private String userID;

    private String password;
    private boolean ban = false;

    private String productID;
    private int product_units;

    private String new_productID;
    private int new_product_units;

    private String productToRemove;

    @EJB
    AdminController cont;

    public AdminView() {
        signedIn = false;
        cont = new AdminController();
        ban = false;
    }

    public String getProductToRemove() {
        return productToRemove;
    }

    public void setProductToRemove(String productToRemove) {
        this.productToRemove = productToRemove;
    }

    public String getNew_productID() {
        return new_productID;
    }

    public void setNew_productID(String new_productID) {
        this.new_productID = new_productID;
    }

    public int getNew_product_units() {
        return new_product_units;
    }

    public void setNew_product_units(int new_product_units) {
        this.new_product_units = new_product_units;
    }

    /**
     * isSignedIn redirection
     */
    public void autoRedirect() {
        if (!signedIn) {

            try {

                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() + "/faces/index.xhtml");
            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }
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

    /**
     * checks if logged in
     */
    public void login() {
        signedIn = cont.login(userID, password);
        if (!signedIn) {
            //lägger till felmeddelande som kan ses i jsf filen
            FacesContext.getCurrentInstance().addMessage("loginCredentials",
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "login failed", "Admin credentials does not work"));
        }

    }

    /**
     * banning a user(customer)
     */
    public void customerBann() {
        cont.customerBann(userID, ban);
    }

    /**
     * Admin ads a product.
     */
    public void addProduct() {
        cont.addProduct(new_productID, new_product_units);
        new_productID = "";
        new_product_units = 0;
    }

    /**
     * Admin removes a product.
     */
    public void removeProduct() {
        cont.removeProduct(productToRemove);
    }

    /**
     * Admin adds product units.
     */
    public void addProductUnits() {
        cont.addProductUnits(productID, product_units);
    }

    /**
     * Get customers list for banning. 
     * @return 
     */
    public List<Customer> getCustomers() {
        return cont.listCustomer();
    }

    /**
     * Get product lists.
     * @return 
     */
    public List<Product> getProducts() {
        return cont.listProduct();
    }

    /**
     * Sign out from Admin area.
     */
    public void logout() {
        signedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
