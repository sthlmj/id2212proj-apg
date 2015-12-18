package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model - Admin.java using this data tier to manage entity(DB).
 *
 * @author guuurris
 */
@Entity
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;


    public Admin() {
    }

    public Admin(String id) {
        this.id = id;
    }

    /**
     * Retrieves admin password.
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set admin password.
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get admin id
     * @return 
     */
    public String getId() {
        return id;
    }

    /**
     * Set admin id
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * String representation of the object
     * @return 
     */
    @Override
    public String toString() {
        return "model.Admin[ id=" + id + " ]";
    }

}
