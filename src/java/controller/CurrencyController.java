package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Currency;

/**
 * Java Persistence API
 * @author guuurris
 */

/**
 * A stateless session bean is a type of enterprise bean which is normally used to do independent operations. 
 * A stateless session bean as per its name does not have any associated client state, but it may preserve its instance state. 
 * EJB Container normally creates a pool of few stateless bean's objects and use these objects to process client's request. 
 * Because of pool, instance variable values are not guaranteed to be same across lookups/method calls.
 */
@Stateless
public class CurrencyController {

    @PersistenceContext(unitName = "ID2212Hmw4PU")
    private EntityManager em;

    //Manages Entity
    public EntityManager getEntityManager() {
        return em;
    }

    //Converts currencies
    public float convertCurrency(Float amount, String from, String to) {

        Currency cFrom = em.find(Currency.class, from);
        Currency cTo = em.find(Currency.class, to);

        return amount * (cTo.getConversionValue() / cFrom.getConversionValue());
    }

    /**
     * Lists available currencies
     *
     * @return list of currencies
     */
    public List<Currency> allCurrencies() {
        List result = em.createQuery("SELECT c FROM Currency c").getResultList();
        return (List<Currency>) result;
    }
}
