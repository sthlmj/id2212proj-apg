package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Customer;


@Stateless
public class CustomerController {
    @PersistenceContext(unitName = "id2212proj-apgPU")
    private EntityManager em;

    //Manages Entity
    public EntityManager getEntityManager() {
        return em;
    }
    
    
    public boolean register(String username, String password){
        
        Customer c =  em.find(Customer.class, username);
        
        if(c != null) {
            return false;
        }
        em.persist(new Customer(username,password));
        
        return true;
    }
     
    
     /**
     * Lists all customers
     *
     * @return list of currencies
     */
    public List<Customer> allUsers() {
        List result = em.createQuery("SELECT c FROM Customer c").getResultList();
        return (List<Customer>) result;
    }
    
   
    
    public boolean login(String username, String password){
        Customer c =  em.find(Customer.class, username);
        if(c == null){
            return false; 
        }
        
        if( c.getPassword().equals(password) && !c.isBanned()){

            return true;
        }
        else{
            return false; 
        }
    }
   
}
