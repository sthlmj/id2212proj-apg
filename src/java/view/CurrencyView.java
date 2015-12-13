
package view;

import controller.CurrencyController;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Currency;

/**
 *
 * The CurrencyView Managed View
 * @author guuurris
 */
@Named(value = "currencyView")          //bean name. This is the name that shall be used in EL expressions in the XHTML files.
@SessionScoped                          //Session scope persists across multiple HTTP requests in a web application.
public class CurrencyView implements Serializable {

    @EJB
    private CurrencyController cont;    //this is an ejb

    private Float amount;
    private String fromCurrency;
    private String toCurrency;
    private float convertedValue;

    public List<Currency> getCurrencies() {
        return cont.allCurrencies();
    }

    public CurrencyView() {
        cont = new CurrencyController();
        convertedValue = 0;
        amount = 0.0F;
    }

    /**
     * JSF setters and getters
     * @return 
     */
    public float getConvertedValue() {
        return convertedValue;
    }

    private void setConvertedValue(float convertedValue) {
        this.convertedValue = convertedValue;
    }

    /**
     * Business Logic. Function that convert currency.
     */
    public void convertCurrency() {
        convertedValue = cont.convertCurrency(amount, fromCurrency, toCurrency);

    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public Float getAmount() {
        return amount;
    }
}
