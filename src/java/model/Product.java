package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_type")
    private String product_type;

    @Column(name = "nr_units")
    private int nr_units;

    public int getUnits() {
        return nr_units;
    }

    public void setUnits(int units) {
        this.nr_units = units;
    }

    public String getId() {
        return product_type;
    }

    public void setId(String id) {
        this.product_type = id;
    }

    public Product() {

    }

    public Product(String type, int units) {
        this.product_type = type;
        this.nr_units = units;
    }

    @Override
    public String toString() {
        return "model.Product[ id=" + product_type + " ]";
    }

}
