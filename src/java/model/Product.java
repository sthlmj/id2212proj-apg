
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
    @Column(name="type")
    private String type;
    
    @Column(name="units")
    private int units;

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
    

    public String getId() {
        return type;
    }

    public void setId(String id) {
        this.type = id;
    }

    public Product() {
    
    }
    public Product(String type, int units) {
        this.type = type;
        this.units = units;
    }
   

    
    @Override
    public String toString() {
        return "model.Product[ id=" + type + " ]";
    }
    
}

