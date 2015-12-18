package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model - Customer.java using this data tier to manage entity(DB).
 *
 * @author guuurris
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String userid;

    @Column(name = "password")
    private String password;

    @Column(name = "banned")
    private boolean banned;

    /**
     * Get customer password.
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set customer password.
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Ban customer.
     */
    public Customer() {
        this.banned = false;
    }

    /**
     * Unban customer.
     * @param userid
     * @param password 
     */
    public Customer(String userid, String password) {
        this.userid = userid;
        this.password = password;
        this.banned = false;
    }

    /**
     * Check if customer banned.
     * @return 
     */
    public boolean isBanned() {
        return banned;
    }

    /**
     * Ban customer.
     * @param banned 
     */
    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getId() {
        return userid;
    }

    public void setId(String id) {
        this.userid = id;
    }

    /**
     * String representation of the object
     * @return 
     */
    @Override
    public String toString() {
        return "model.Customer[ id=" + userid + " ]";
    }
}
