/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Admin;
import model.Customer;
import model.Product;

/**
 *
 * @author guuurris
 */
@Stateless
public class AdminController {
    @PersistenceContext(unitName = "id2212proj-apgPU")
    private EntityManager em;

   
    public boolean login(String userID, String password) {
        Admin admin = em.find(Admin.class, userID);
        if(admin == null){
            return false;
        }
        else if(admin.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void customerBann(String userID, boolean banned){
        Customer new_customer_state = em.find(Customer.class, userID);
        new_customer_state.setBanned(banned);
        em.merge(new_customer_state);
    }
    
    public void addProduct(String productType, int units){
        em.persist(new Product(productType, units));
    }
    
    public void addProductUnits(String productID, int increaseBy){
        Product product = em.find(Product.class, productID);
        product.setUnits(product.getUnits() + increaseBy);
        em.merge(product);
    }
    
    public void removeProduct(String productID){
      Product to_remove = em.find(Product.class, productID);
      em.remove(to_remove);
    }
    
    public List<Customer> listCustomer(){
       List result = em.createQuery("SELECT c FROM Customer c").getResultList();
        return (List<Customer>) result;
    }
}
