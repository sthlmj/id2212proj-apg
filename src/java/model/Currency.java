package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The currency Entity(db)
 * @author guuurris
 */
@Entity
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String currency;

    //the ratio value compared to SEK
    @Column(name = "ratio")
    private float conversionValue;

    public float getConversionValue() {
        return conversionValue;
    }

    public void setConversionValue(float conversionValue) {
        this.conversionValue = conversionValue;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
        return currency;
    }

    @Override
    public String toString() {
        return "model.Currency[ id=" + currency + " ]";
    }
}
