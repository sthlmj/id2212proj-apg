package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Admin;
import model.Customer;
import model.Product;

/**
 * Controller - AdminController.java Using this business logic to handle
 * requests from AdminView.java
 *
 * @author guuurris
 */
@Stateless
public class AdminController {

    @PersistenceContext(unitName = "id2212proj-apgPU")
    private EntityManager em;

    /**
     * Logs in admin
     * @param userID
     * @param password
     * @return 
     */
    public boolean login(String userID, String password) {
        Admin admin = em.find(Admin.class, userID);
        if (admin == null) {
            return false;
        } else if (admin.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * Ban customer aka. user.
     * @param userID
     * @param banned
     */
    public void customerBann(String userID, boolean banned) {
        Customer new_customer_state = em.find(Customer.class, userID);
        new_customer_state.setBanned(banned);
        em.merge(new_customer_state);
    }

    /**
     * Add new product.
     * @param productType
     * @param units 
     */
    public void addProduct(String productType, int units) {
        em.persist(new Product(productType, units));
    }

    /**
     * Adjust product unit.
     * @param productID
     * @param increaseBy 
     */
    public void addProductUnits(String productID, int increaseBy) {
        Product product = em.find(Product.class, productID);

        if (product.getUnits() + increaseBy < 0) {
            product.setUnits(product.getUnits() + increaseBy);
        }
        product.setUnits(product.getUnits() + increaseBy);

        em.merge(product);
    }

    /**
     * Remove a product.
     * @param productID 
     */
    public void removeProduct(String productID) {
        Product to_remove = em.find(Product.class, productID);
        em.remove(to_remove);
    }

    /**
     * List customers. JPQL
     * @return
     */
    public List<Customer> listCustomer() {
        List result = em.createQuery("SELECT c FROM Customer c").getResultList();
        return (List<Customer>) result;
    }

    /**
     * List products. JPQL
     * @return 
     */
    public List<Product> listProduct() {
        List result = em.createQuery("SELECT p FROM Product p").getResultList();
        return (List<Product>) result;
    }
}
