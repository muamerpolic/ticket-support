package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.Constraint;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import play.data.format.*;
import play.data.validation.*;
import sun.misc.Contended;
import com.avaje.ebean.annotation.JsonIgnore;


/**
 * Created by muamerpolic on 6/15/17.
 */
@Entity
public class User extends Model{

    @Id
    public Long Id;

    @Constraints.Required
    @Constraints.Email
    public String emailAdress;
    public String getEmailAdress(){
        return emailAdress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAdress = emailAddress;
    }

    @Constraints.Required
    @Constraints.MinLength(6)
    public String password;

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Constraints.Required
    public String fullName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    public List<Ticket> tickets = new ArrayList<Ticket>();


    public User(String emailAddress, String password, String fullName) {
        setEmailAddress(emailAddress);
        setPassword(password);
        this.fullName = fullName;
    }

    public static Finder<Long, User> find = new Finder<Long,User>(User.class);
}
