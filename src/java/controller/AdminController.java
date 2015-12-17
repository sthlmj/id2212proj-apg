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
     * Admin handles customerBann, addProduct, addProductUnits, removeProduct,
     *
     * @param userID
     * @param banned
     */
    public void customerBann(String userID, boolean banned) {
        Customer new_customer_state = em.find(Customer.class, userID);
        new_customer_state.setBanned(banned);
        em.merge(new_customer_state);
    }

    public void addProduct(String productType, int units) {
        em.persist(new Product(productType, units));
    }

    public void addProductUnits(String productID, int increaseBy) {
        Product product = em.find(Product.class, productID);

        if (product.getUnits() + increaseBy < 0) {
            product.setUnits(product.getUnits() + increaseBy);
        }
        product.setUnits(product.getUnits() + increaseBy);

        em.merge(product);
    }

    public void removeProduct(String productID) {
        Product to_remove = em.find(Product.class, productID);
        em.remove(to_remove);
    }

    /**
     * JPQL getting list of customers and list of products
     *
     * @return
     */
    public List<Customer> listCustomer() {
        List result = em.createQuery("SELECT c FROM Customer c").getResultList();
        return (List<Customer>) result;
    }

    public List<Product> listProduct() {
        List result = em.createQuery("SELECT p FROM Product p").getResultList();
        return (List<Product>) result;
    }
}
