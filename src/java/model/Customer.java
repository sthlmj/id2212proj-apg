/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public Customer(){
        
    }
    
    public Customer(String userid, String password){
        this.userid = userid;
        this.password = password;
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
