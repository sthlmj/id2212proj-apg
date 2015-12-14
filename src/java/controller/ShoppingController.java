/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import model.Product;

/**
 *
 * @author joehulden
 */

@Stateless
public class ShoppingController {

    @PersistenceContext(unitName = "id2212proj-apgPU")
    private EntityManager em;
    
    @Resource
    private SessionContext context;

    @Resource
    private UserTransaction usertransaction;
    
    //Manages Entity
    public EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * 
     * @param type - product type
     * @param units - number of products
     * @return Product if match null else
     */
    public Product takeProduct(String type, int units){
        try{
           return (Product) em.createQuery("SELECT p FROM Product p WHERE type=:type AND unit <=:value").setParameter("type", units).setParameter("value", units).getResultList().get(0);
        } catch (Exception e){
            return null;
 
        }
    }
    
    /**
     * This method demands that we have an open transaction 
     * so that products can be bought
     * @return if products was bought or not
     */
    
    public boolean buy(List<Product> list){
       for(Product p : list){
           
           Product onMarketP = (Product) em.createQuery("SELECT p FROM Product p WHERE unit <=:value").setParameter("type", p.getId()).setParameter("value", p.getUnits()).getResultList().get(0);
           
           //we buy a part of all the products
           if (onMarketP == null){
               context.getRollbackOnly();
               return false;
           }
           //If we buy the last products on the market
           if(onMarketP.getUnits() == p.getUnits()){
               em.remove(onMarketP);
           }
           else { // uppdate product information
               em.merge(new Product(p.getId(),onMarketP.getUnits() - p.getUnits()));
           }
          
       }
        
        return true;
    }
    
    /**
     * The full product list
     * @return - a list of all the products
     */
    public List<Product> listProducts(){
      return em.createQuery("SELECT p FROM Product p").getResultList();     
    }
    
}
