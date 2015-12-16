
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
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

     //the ratio value compared to SEK
    @Column(name = "password")
    private String password;

    @Column(name = "banned")
    private boolean banned;

    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public Customer(){
        this.banned = false;
    }
    
    public Customer(String userid, String password){
        this.userid = userid;
        this.password = password;
        this.banned = false;
    }
    
    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    public String getId() {
        return userid;
    }

    public void setId(String id) {
        this.userid = id;
    }

  


    @Override
    public String toString() {
        return "model.Customer[ id=" + userid + " ]";
    }
    
}
