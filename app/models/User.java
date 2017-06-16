package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.Constraint;

import com.avaje.ebean.Ebean;
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

    private String authToken;

    public String createToken() {
        authToken = UUID.randomUUID().toString();
        save();
        return authToken;
    }

    public void deleteAuthToken() {
        authToken = null;
        save();
    }

    @Id
    public Long Id;

    @Constraints.Required
    @Constraints.Email
    public String emailAdress;
    public String getEmailAdress(){
        return emailAdress;
    }
    public void setEmailAdress(String emailAddress) {
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
        setEmailAdress(emailAddress);
        setPassword(password);
        this.fullName = fullName;
    }

    public static User findByAuthToken(String authToken) {
        if (authToken == null) {
            return null;
        }

        try  {
            return find.where().eq("authToken", authToken).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static User findByEmailAddressAndPassword(String emailAddress, String password) {
        return find.where().eq("emailAdress", emailAddress.toLowerCase()).eq("password", password).findUnique();
    }

    public static Finder<Long, User> find = new Finder<Long,User>(User.class);
}
