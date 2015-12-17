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
    SessionContext context;

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
    public Product takeProduct(String type, int units) {

        System.out.println("takeproduct");
        // try{
        if (em.createQuery("SELECT p FROM Product p WHERE p.product_type=:pType AND p.nr_units>=:NUnits")
                .setParameter("pType", type)
                .setParameter("NUnits", units).getResultList().size() > 0) {
            return new Product(type, units);
        } else {
            return null;
        }
        /*   
           return (Product) em.createQuery("SELECT p FROM Product p WHERE p.product_type=:pType AND p.nr_units>=:NUnits").setParameter("pType", type).setParameter("NUnits", units).getResultList().get(0);
        } catch (Exception e){
            e.printStackTrace();
            return null;
 
        }*/
    }

    /**
     * This method demands that we have an open transaction so that products can
     * be bought
     *
     * @return if products was bought or not
     */
    public boolean buy(List<Product> list) throws Exception {

        System.out.println("entering buy method list size: " + list.size());
        for (Product p : list) {
            System.out.println("foreach loop");

            Product onMarketP = null;

            List<Product> List_onMarketP = (List<Product>) em.createQuery("SELECT p FROM Product p WHERE p.product_type=:pType AND p.nr_units>=:NUnits")
                    .setParameter("pType", p.getId()).setParameter("NUnits", p.getUnits()).getResultList();

            if (List_onMarketP.isEmpty()) {
                context.setRollbackOnly();
                throw new Exception("Buy rolledback (Transaction could not be) ");

            } else {
                onMarketP = List_onMarketP.get(0);
            }

            //we buy a part of all the products
            if (onMarketP == null) {
                context.setRollbackOnly();
                throw new Exception("Buy rolledback (Transaction could not be) ");
            }
            //If we buy the last products on the market
            if (onMarketP.getUnits() == p.getUnits()) {
                System.out.println("if case 2");
                em.remove(onMarketP);
            } else { // uppdate product information
                System.out.println("Merge operation called on product(" + onMarketP.getId() + "): " + (onMarketP.getUnits() - p.getUnits()));
                em.merge(new Product(onMarketP.getId(), onMarketP.getUnits() - p.getUnits()));

            }

        }

        return true;
    }

    /**
     * The full product list
     *
     * @return - a list of all the products
     */
    public List<Product> listProducts() {
        return em.createQuery("SELECT p FROM Product p").getResultList();
    }

}
